package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepFirstLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddIndexSql;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddPrimaryKeySql;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Service
public class LIndexedColumns {
    public final static String tableName = "V_BD_INDEXED_COLOMNS";

    @Autowired
    private PluginTunes pluginTunes;

    @Autowired
    private AddPrimaryKeySql primaryKeySql;

    @Autowired
    private AddIndexSql addIndexSql;
    @Autowired
    private AddConstraintSql constraintSql;

    public QueryTableNew additionOne() {

        QueryTableNew queryTable = new QueryTableNew();

        String s = "create table " + pluginTunes.getUser() + "." + tableName + "\n" +
                "(ID    NUMBER not null,\n" +
                "COLLECTION_ID NUMBER,\n" +
                "COLUMN_REF   NUMBER  not null \n" +
                ") tablespace \n" + pluginTunes.getTableSpaseSysTable() + "\n" +
                pluginTunes.getStorageTable();
        queryTable.add(s);

        s = primaryKeySql.generateSys(tableName);
        queryTable.add(s);

        s = addIndexSql.generateSys(tableName, false, "COLLECTION_ID");
        queryTable.add(s);

        s = constraintSql.getSql(tableName, "COLUMN_REF", "V_BD_OBJECT", "ID");
        queryTable.add(s);


        return queryTable;
    }
}
