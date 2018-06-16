package ru.vood.Plugin.Refactoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vood.Plugin.db.DBConnect;
import ru.vood.Plugin.dialogs.ErrWin;
import ru.vood.core.runtime.exception.ApplicationErrorException;

import java.sql.*;
import java.util.TreeMap;

@Deprecated
class DbQuery {
    private final static Logger lOG = LoggerFactory.getLogger(DbQuery.class);

    public static TreeMap<String, OpenedCursor> treeSet = new TreeMap<>();

    static ResultSet executeQuery(String query) {
        Connection conn;
        PreparedStatement stmt = null;
        ResultSet r = null;
        conn = DBConnect.getConnection();
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    stmt = conn.prepareStatement(query);
                    r = stmt.executeQuery();

                    treeSet.put(r.toString(), new OpenedCursor(r, Thread.currentThread().getStackTrace()));
                    lOG.debug(" Выполнен запрос. " + query + " Результат " + r);
                } else {
                    lOG.debug(" Нет соединения. ");
                }
            } catch (SQLException e) {
                throw new ApplicationErrorException(" Не удалось Выполнить запроос: " + query + " Причина " + e.getMessage(), e);
            }
        }
        return r;
    }

    @Deprecated
    public static ResultSet executeSelectQuery(String query) {
        Connection conn;
        Statement stmt;
        ResultSet r = null;
        try {
            conn = DBConnect.getConnection();
            if (!conn.isClosed()) {
                stmt = conn.createStatement();
                r = stmt.executeQuery(query);
                treeSet.put(r.toString(), new OpenedCursor(r, Thread.currentThread().getStackTrace()));
                lOG.debug(" Выполнен запрос. " + query + " Результат " + r);

            } else {
                lOG.debug(" Нет соединения. ");
            }
        } catch (SQLException e) {
            String s = " Не удалось Выполнить запроос: ";
            new ErrWin(null, s, true, s + query, e);
            r = null;
        }
        return r;
    }


    static void setContext(String par, String val) {
        ResultSet resultSet;
/*        if (AppConst.getTune(ListTunes.USER).toUpperCase().equals("IBS")) {
            resultSet = executeQuery("call executor.SET_CONTEXT('" + par + "','" + val + "') ");
        } else {*/
        resultSet = executeQuery("call sys.set_voodcontext_value('" + par + "','" + val + "') ");
        //}
        DbQuery.closeCursor(resultSet);
    }

    static void closeCursor(ResultSet resultSet) {
        try {
            treeSet.remove(resultSet.toString());
            resultSet.getStatement().close();
            resultSet.close();
        } catch (SQLException e) {
            throw new ApplicationErrorException("Не удалось закрыть курсор", e);
        }
    }


    @Deprecated
    static class OpenedCursor {
        ResultSet resultSet;
        boolean opened;
        StackTraceElement[] stack;

        public OpenedCursor(ResultSet resultSet, StackTraceElement[] stack) {
            this.resultSet = resultSet;
            this.stack = stack;
        }

    }
}

