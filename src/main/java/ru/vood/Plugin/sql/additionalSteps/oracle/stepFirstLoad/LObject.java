package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.QueryTableNew;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.sql.dbms.oracle.AddIndexSql;
import ru.vood.Plugin.sql.dbms.oracle.AddPrimaryKeySql;

@Service
public class LObject {

    public final static String tableName = "V_BD_OBJECT";

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
                "PARENT NUMBER,\n" +
                "TYPE_OBJECT NUMBER not null,\n" +
                "DATE_CREATE DATE default sysdate,\n" +
                "JAVA_CLASS VARCHAR2(512) not null\n" +
                ") tablespace \n" + pluginTunes.getTableSpaseSysTable() + "\n " +
                pluginTunes.getStorageTable();
        queryTable.add(s);

        s = primaryKeySql.generateSys(tableName);
        queryTable.add(s);

        s = constraintSql.getSql(tableName, "PARENT", tableName, "ID");
        queryTable.add(s);

        s = constraintSql.getSql(tableName, "TYPE_OBJECT", "V_BD_OBJECT_TYPE", "ID");
        queryTable.add(s);

        String[] col = {"CODE", "PARENT", "TYPE_OBJECT"};
        s = addIndexSql.generateSys(tableName, true, col);
        queryTable.add(s);

        return queryTable;
    }
}
