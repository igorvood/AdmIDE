package ru.vood.Plugin.db;

public enum SupportedDBMS {
    ORACLE("ORACLE"), MYSQL("MYSQL");

    private String type;

    private SupportedDBMS(String name) {
        this.type = name;
    }

    public String getType() {
        return type;
    }

}
