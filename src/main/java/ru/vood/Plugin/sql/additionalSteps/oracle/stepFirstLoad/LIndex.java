package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.db.QueryTable;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.sql.dbms.oracle.AddPrimaryKeySql;
import ru.vood.core.runtime.type.Varchar2;

public class LIndex extends StepsFirstLoad {

    @Override
    QueryTable additionOne(QueryTable queryTable) {
        if (queryTable == null) {
            queryTable = new QueryTable();
        }
        String tableName = "V_BD_INDEX";

        String s = "create table " + AppConst.getTune(ListTunes.USER) + "." + tableName + "\n" +
                "(ID    NUMBER not null,\n" +
                "UNIQUE_I   VARCHAR2(1),\n" +
                "GLOBAL_I   VARCHAR2(1),\n" +
                "LIST_COLUMNS   VARCHAR2(250) not null \n" +
                ") tablespace \n" + AppConst.getTune(ListTunes.TABLE_SPASE_SYS_TABLE) + "\n" +
                AppConst.getTune(ListTunes.STORAGE_TABLE);
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddPrimaryKeySql.generateSys(tableName);
        queryTable.set(queryTable.count().add(1), new Varchar2(s));


        s = AddConstraintSql.getSql(tableName, "ID", "V_BD_OBJECT", "ID");
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        return queryTable;
    }

}
