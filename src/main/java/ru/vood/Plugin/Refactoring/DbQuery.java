package ru.vood.Plugin.Refactoring;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.db.DBConnect;
import ru.vood.Plugin.dialogs.ErrWin;
import ru.vood.Plugin.logging.Log;
import ru.vood.core.runtime.exception.ApplicationErrorException;

import java.sql.*;
import java.util.TreeMap;
import java.util.logging.Level;

class DbQuery {
    public static TreeMap<String, OpenedCursor> treeSet = new TreeMap<>();
    private static Log log = Log.getLogger(DbQuery.class);

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
                    log.putToLog(" Выполнен запрос. " + query + " Результат " + r, Level.FINEST);
                } else {
                    log.putToLog(" Нет соединения. ", Level.WARNING);
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
                log.putToLog(" Выполнен запрос. " + query + " Результат " + r, Level.FINEST);

            } else {
                log.putToLog(" Нет соединения. ", Level.WARNING);
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
        if (AppConst.getTune(ListTunes.USER).toUpperCase().equals("IBS")) {
            resultSet = executeQuery("call executor.SET_CONTEXT('" + par + "','" + val + "') ");
        } else {
            resultSet = executeQuery("call sys.set_voodcontext_value('" + par + "','" + val + "') ");
        }
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

    //todo клас временный для контороля открытых курсоров, удалить поже когда не нужен будет
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

