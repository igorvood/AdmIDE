package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepFirstLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddIndexSql;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddPrimaryKeySql;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Service
public class LObjType {

    public final static String tableName = "V_BD_OBJECT_TYPE";

    @Autowired
    private PluginTunes pluginTunes;

    @Autowired
    private AddPrimaryKeySql primaryKeySql;

    @Autowired
    private AddConstraintSql constraintSql;

    @Autowired
    private AddIndexSql addIndexSql;

    public QueryTableNew additionOne() {
        QueryTableNew queryTable = new QueryTableNew();

        String s = "create table " + pluginTunes.getUser() + "." + tableName + "\n" +
                "(ID    NUMBER not null,\n" +
                "CODE   VARCHAR2(50) not null,\n" +
                "NAME   VARCHAR2(250) not null,\n" +
                "NEED_DDL   VARCHAR2(1),\n" +
                "PARENT NUMBER\n" +
                ") tablespace \n" + pluginTunes.getTableSpaseSysTable() + "\n " +
                pluginTunes.getStorageTable();
        queryTable.add(s);

        s = primaryKeySql.generateSys(tableName);
        queryTable.add(s);

        s = constraintSql.getSql(tableName, "PARENT", tableName, "ID");
        queryTable.add(s);

        s = addIndexSql.generateSys(tableName, true, false, "CODE");
        queryTable.add(s);

        return queryTable;
    }
}
