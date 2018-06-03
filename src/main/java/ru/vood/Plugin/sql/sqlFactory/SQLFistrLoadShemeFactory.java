package ru.vood.Plugin.sql.sqlFactory;


import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.applicationConst.SupportedDBMS;
import ru.vood.Plugin.sql.dbms.oracle.SQLFistrLoadShemeOra;
import ru.vood.Plugin.sql.sqlInterfaces.SQLFistrLoadShemeInterface;

public class SQLFistrLoadShemeFactory implements SQLFistrLoadShemeInterface {
    private static SQLFistrLoadShemeInterface sql;

    private SQLFistrLoadShemeFactory() {
    }

    public static SQLFistrLoadShemeInterface getInstance() {
        if (sql == null) {
            if (AppConst.getTune(ListTunes.DBMS_TYPE) == SupportedDBMS.ORACLE.getType()) {
                sql = new SQLFistrLoadShemeOra();
            } else sql = null;
        }
        return sql;
    }

    @Override
    public void getSQL() {
        sql.getSQL();
    }


/*    public QueryTable getSQLForCreate(String owner, String storage, String tableName, String context) {
        return sql.getSQLForCreate(owner, storage, tableName, context);
    }

    public QueryTable getSQLForCreate() {
        return sql.getSQLForCreate(DbConst.OWNER_DICT, DbConst.STOGAGE_DICT, DbConst.MAIN_TABLE, DbConst.CONTEXT);
    }

    public QueryTable getSQLForInsertData(String owner, String tableName) {
        return sql.getSQLForInsertData(owner, tableName);
    }

    public QueryTable getSQLForInsertData() {
        return sql.getSQLForInsertData(DbConst.OWNER_DICT, DbConst.MAIN_TABLE);
    }*/
}
