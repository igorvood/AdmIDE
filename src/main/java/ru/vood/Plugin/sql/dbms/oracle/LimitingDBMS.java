package ru.vood.Plugin.sql.dbms.oracle;

public class LimitingDBMS {
    private static int maxLengthNameObject = 30;

    public static String getNameObj(String name) {
        if (name.length() <= maxLengthNameObject) {
            return name;
        }
        return name.substring(0, maxLengthNameObject - 1);
    }
}
