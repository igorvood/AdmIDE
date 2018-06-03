package ru.vood.Plugin.applicationConst;

public enum TypeObject {

    TABLE("TABLE", 10),
    REFERENCE("REFERENCE", 20),
    ARRAY("ARRAY", 30),
    STRING("STRING", 40),
    NUMBER("NUMBER", 50),
    DATE("DATE", 60),
    BOOLEAN("BOOLEAN", 60),
    COLOMN("COLOMN", 70);

    private String name;
    private int id_num;

    private TypeObject(String name, int id_num) {
        this.name = name;
        this.id_num = id_num;
    }

    public int getId_num() {
        return id_num;
    }

    public void setId_num(int id_num) {
        this.id_num = id_num;
    }

    public String getName() {

        return name;
    }

}
