package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.db.QueryTable;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.sql.dbms.oracle.AddPrimaryKeySql;
import ru.vood.core.runtime.type.Varchar2;

public class LTable extends StepsFirstLoad {
    @Override
    QueryTable additionOne(QueryTable queryTable) {
        if (queryTable == null) {
            queryTable = new QueryTable();
        }
        String tableName = "V_BD_TABLE";

        String s = "create table " + AppConst.getTune(ListTunes.USER) + "." + tableName + "\n" +
                "(ID    NUMBER not null,\n" +
                "TABLE_SPACE   VARCHAR2(50) ,\n" +
                "STORAGE   VARCHAR2(500), \n" +
                "  to_type     NUMBER,\n" + //VARCHAR2(500)
                "  length      NUMBER,\n" +
                "  precision   NUMBER\n" +
                ") tablespace \n" + AppConst.getTune(ListTunes.TABLE_SPASE_SYS_TABLE) + "\n " +
                AppConst.getTune(ListTunes.STORAGE_TABLE);
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddPrimaryKeySql.generateSys(tableName);
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddConstraintSql.getSql(tableName, "ID", "V_BD_OBJECT", "ID");
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddConstraintSql.getSql(tableName, "to_type", tableName, "ID");
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        return queryTable;
    }
}
