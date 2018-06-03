package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.db.QueryTable;
import ru.vood.Plugin.logging.Log;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.sql.dbms.oracle.AddIndexSql;
import ru.vood.Plugin.sql.dbms.oracle.AddPrimaryKeySql;
import ru.vood.core.runtime.type.Varchar2;

public class LObjType extends StepsFirstLoad {

    private static Log log = Log.getLogger(LObjType.class);

    @Override
    public QueryTable additionOne(QueryTable queryTable) {
        if (queryTable == null) {
            queryTable = new QueryTable();
        }
        String tableName = "V_BD_OBJECT_TYPE";

        String s = "create table " + AppConst.getTune(ListTunes.USER) + "." + tableName + "\n" +
                "(ID    NUMBER not null,\n" +
                "CODE   VARCHAR2(50) not null,\n" +
                "NAME   VARCHAR2(250) not null,\n" +
                "PARENT NUMBER\n" +

                ") tablespace \n" + AppConst.getTune(ListTunes.TABLE_SPASE_SYS_TABLE) + "\n " +
                AppConst.getTune(ListTunes.STORAGE_TABLE);
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddPrimaryKeySql.generateSys(tableName);
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddConstraintSql.getSql(tableName, "PARENT", tableName, "ID");
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        s = AddIndexSql.generateSys(tableName, true, false, "CODE");

        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        return queryTable;
    }
}
