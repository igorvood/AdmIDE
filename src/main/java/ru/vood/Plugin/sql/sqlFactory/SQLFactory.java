package ru.vood.Plugin.sql.sqlFactory;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.applicationConst.SupportedDBMS;
import ru.vood.Plugin.sql.oracle.SQLOra;
import ru.vood.Plugin.sql.sqlInterfaces.SQLInterface;

import java.math.BigDecimal;


public class SQLFactory implements SQLInterface {
    private static SQLInterface sql;

    private SQLFactory() {
    }

    public static SQLInterface getInstance() {
        if (sql == null) {
            if (AppConst.getTune(ListTunes.DBMS_TYPE).equals(SupportedDBMS.ORACLE.getType())) {
                sql = new SQLOra();
            } else sql = null;
        }
        return sql;
    }

    public String getSQLForAddCollectionId(String tableShortName) {
        return sql.getSQLForAddCollectionId(tableShortName);
    }

    public String getSQLNextId() {
        return sql.getSQLNextId();
    }

    public BigDecimal getNextId() {
        return sql.getNextId();
    }

    public String getIsExistsQuery(Object o) {
        return sql.getIsExistsQuery(o);
    }
}
