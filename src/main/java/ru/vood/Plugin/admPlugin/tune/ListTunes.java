package ru.vood.Plugin.admPlugin.tune;

@Deprecated
public enum ListTunes {
    PACKAGE("PACKAGE", 10),
    PASSWORD("PASSWORD", 20),
    HOST("HOST", 30),
    PORT("PORT", 40),
    SID("SID", 50),
    TABLE_SPASE_SYS_TABLE("TABLE_SPASE_SYS_TABLE", 60),
    TABLE_SPASE_SYS_INDEX("TABLE_SPASE_SYS_INDEX", 70),
    TABLE_SPASE_USER_TABLE("TABLE_SPASE_USER_TABLE", 80),
    TABLE_SPASE_USER_INDEX("TABLE_SPASE_USER_INDEX", 90),
    ENCODDING("ENCODDING", 100),
    PREFIX_TABLE("PREFIX_TABLE", 110),
    PREFIX_COLOMN("PREFIX_COLOMN", 111),
    DBMS_TYPE("DBMS_TYPE", 120),
    USER("USER", 130),
    OWNER("OWNER", 140),
    STORAGE_TABLE("STORAGE_TABLE", 150),
    STORAGE_INDEX("STORAGE_INDEX", 160),
    DEFAULT_FOLDER("DEFAULT_FOLDER", 1000);


    private String name;
    private int id_num;

    private ListTunes(String name, int id_num) {
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
