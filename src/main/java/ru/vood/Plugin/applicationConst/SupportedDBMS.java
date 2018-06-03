package ru.vood.Plugin.applicationConst;

/**
 * Created by balitskiy on 30/03/2016.
 */
public enum SupportedDBMS {
    ORACLE("ORACLE");

    private String type;

    private SupportedDBMS(String name) {
        this.type = name;
    }

    public String getType() {
        return type;
    }

}
