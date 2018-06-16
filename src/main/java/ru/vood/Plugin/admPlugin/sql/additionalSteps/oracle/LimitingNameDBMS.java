package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle;

import org.springframework.stereotype.Component;

@Component
public class LimitingNameDBMS {

    private static int maxLengthNameObject = 30;

    public static String getNameObj(String name) {
        if (name.length() <= maxLengthNameObject) {
            return name;
        }
        return name.substring(0, maxLengthNameObject - 1);
    }
}
