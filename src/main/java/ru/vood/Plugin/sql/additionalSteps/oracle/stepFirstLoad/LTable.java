package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.QueryTableNew;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.sql.dbms.oracle.AddPrimaryKeySql;


@Service
public class LTable {

    @Autowired
    private PluginTunes pluginTunes;

    @Autowired
    private AddPrimaryKeySql primaryKeySql;

    @Autowired
    private AddConstraintSql constraintSql;

    QueryTableNew additionOne() {
        QueryTableNew queryTable = new QueryTableNew();

        String tableName = "V_BD_TABLE";

        String s = "create table " + pluginTunes.getUser() + "." + tableName + "\n" +
                "(ID    NUMBER not null,\n" +
                "TABLE_SPACE   VARCHAR2(50) ,\n" +
                "STORAGE   VARCHAR2(500), \n" +
                "  to_type     NUMBER,\n" + //VARCHAR2(500)
                "  length      NUMBER,\n" +
                "  precision   NUMBER\n" +
                ") tablespace \n" + pluginTunes.getTableSpaseSysTable() + "\n " +
                pluginTunes.getStorageTable();
        queryTable.add(s);

        s = primaryKeySql.generateSys(tableName);
        queryTable.add(s);

        s = constraintSql.getSql(tableName, "ID", "V_BD_OBJECT", "ID");
        queryTable.add(s);

        s = constraintSql.getSql(tableName, "to_type", tableName, "ID");
        queryTable.add(s);

        return queryTable;
    }
}
