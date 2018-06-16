package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.sql.QueryTableNew;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.sql.dbms.oracle.AddPrimaryKeySql;

@Service
public class LIndex {

    protected final static String tableName = "V_BD_INDEX";

    @Autowired
    private PluginTunes pluginTunes;

    @Autowired
    private AddPrimaryKeySql primaryKeySql;

    @Autowired
    private AddConstraintSql constraintSql;

    public QueryTableNew additionOne() {

        QueryTableNew queryTable = new QueryTableNew();

        String s = "create table " + pluginTunes.getUser() + "." + tableName + "\n" +
                "(ID    NUMBER not null,\n" +
                "UNIQUE_I   VARCHAR2(1),\n" +
                "GLOBAL_I   VARCHAR2(1),\n" +
                "COLUMNS   NUMBER not null,\n" +
                "LIST_COLUMNS   VARCHAR2(250) --not null -- Старая колонка \n" +
                ") tablespace \n" + pluginTunes.getTableSpaseSysTable() + "\n" +
                pluginTunes.getStorageTable();
        queryTable.add(s);

        s = primaryKeySql.generateSys(tableName);
        queryTable.add(s);

        return queryTable;
    }

}
