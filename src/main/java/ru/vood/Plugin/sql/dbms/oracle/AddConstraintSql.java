package ru.vood.Plugin.sql.dbms.oracle;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;

public class AddConstraintSql {


    public static String getSql(String tableName, String colomn, String refTableName, String refColomn) {
        String s = "alter table " + AppConst.getTune(ListTunes.USER) + "." + tableName + "\n" +
                "  add constraint " + LimitingDBMS.getNameObj("FK#" + tableName + "_" + colomn + "_" + refTableName) + " foreign key (" + colomn + ")\n" +
                "  references " + AppConst.getTune(ListTunes.USER) + "." + refTableName + " (" + refColomn + ")\n";
        return s;
    }

    public static String getSqlAndAddPrefix(String tableName, String colomn, String refTableName, String refColomn) {
        String s = "alter table " + AppConst.getTune(ListTunes.USER) + "." + AppConst.getTune(ListTunes.PREFIX_TABLE) + tableName + "\n" +
                "  add constraint " + LimitingDBMS.getNameObj("FK#" + AppConst.getTune(ListTunes.PREFIX_TABLE) +
                tableName + "_" + colomn + "_" + AppConst.getTune(ListTunes.PREFIX_TABLE) + refTableName) + " foreign key (" + colomn + ")\n" +
                "  references " + AppConst.getTune(ListTunes.USER) + "." + AppConst.getTune(ListTunes.PREFIX_TABLE) + refTableName + " (" + refColomn + ")\n";
        return s;
    }
}
