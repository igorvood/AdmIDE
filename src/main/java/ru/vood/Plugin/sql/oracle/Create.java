package ru.vood.Plugin.sql.oracle;

@Deprecated
public class Create /*implements CreateInteface*/ {
    /*@Override
    public QueryTable createTable(String tableName, String owner, String storage) {
        QueryTable queryes = new QueryTable();
        Varchar2 qw = new Varchar2("CREATE TABLE " + owner + "." + tableName + "\n" +
                "(ID NUMBER  not null" + "\n" +
                ")" + "\n" +
                storage + "\n" +
                "\n");
        queryes.get(queryes.count().add(1), true).assign(qw);
        return queryes;
    }

    @Override
    public QueryTable createColl(String tableName, String colName) {
        return null;
    }*/
}
