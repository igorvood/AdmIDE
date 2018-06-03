package ru.vood.Plugin.sql.dbms.oracle;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;

public class AddPrimaryKeySql {

    public static String generateUserPK(String tableName) {
        return generate(tableName, ListTunes.TABLE_SPASE_USER_INDEX);
    }

    public static String generateSys(String tableName) {
        return generate(tableName, ListTunes.TABLE_SPASE_SYS_INDEX);
    }

    private static String generate(String tableName, ListTunes tableSpace) {
        String nameConstraint = LimitingDBMS.getNameObj("PK#" + tableName);
        String s = "alter table " + AppConst.getTune(ListTunes.USER) + "." + tableName + "\n" +
                "  add constraint " + nameConstraint + " primary key (ID)\n" +
                "  using index tablespace \n" + AppConst.getTune(tableSpace) + AppConst.getTune(ListTunes.STORAGE_INDEX);
        return s;
    }
}
