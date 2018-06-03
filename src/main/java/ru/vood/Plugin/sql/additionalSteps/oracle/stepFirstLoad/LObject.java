package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.db.QueryTable;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.sql.dbms.oracle.AddIndexSql;
import ru.vood.Plugin.sql.dbms.oracle.AddPrimaryKeySql;
import ru.vood.core.runtime.type.Varchar2;

public class LObject extends StepsFirstLoad {

    @Override
    QueryTable additionOne(QueryTable queryTable) {
        if (queryTable == null) {
            queryTable = new QueryTable();
        }
        String tableName = "V_BD_OBJECT";

        String s = "create table " + AppConst.getTune(ListTunes.USER) + "." + tableName + "\n" +
                "(ID    NUMBER not null,\n" +
                "CODE   VARCHAR2(50) not null,\n" +
                "NAME   VARCHAR2(250) not null,\n" +
                "PARENT NUMBER,\n" +
                "TYPE_OBJECT NUMBER not null,\n" +
                "DATE_CREATE DATE default sysdate,\n" +
                "JAVA_CLASS VARCHAR2(512) not null\n" +
                ") tablespace \n" + AppConst.getTune(ListTunes.TABLE_SPASE_SYS_TABLE) + "\n " +
                AppConst.getTune(ListTunes.STORAGE_TABLE);
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddPrimaryKeySql.generateSys(tableName);
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddConstraintSql.getSql(tableName, "PARENT", tableName, "ID");
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddConstraintSql.getSql(tableName, "TYPE_OBJECT", "V_BD_OBJECT_TYPE", "ID");
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        String[] col = {"CODE", "PARENT", "TYPE_OBJECT"};
        s = AddIndexSql.generateSys(tableName, true, col);
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        return queryTable;
    }
}
