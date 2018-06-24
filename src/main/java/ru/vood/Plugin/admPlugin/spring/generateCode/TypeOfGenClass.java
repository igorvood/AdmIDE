package ru.vood.Plugin.admPlugin.spring.generateCode;

public enum TypeOfGenClass {

    ENTITY_CLASS("Entity"),
    IMPL_CLASS("Impl"),
    SERVICE_CLASS("Service");

    private String type;

    private TypeOfGenClass(String name) {
        this.type = name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
