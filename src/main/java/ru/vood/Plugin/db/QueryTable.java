package ru.vood.Plugin.db;

import ru.vood.core.runtime.type.IndexByTable;
import ru.vood.core.runtime.type.Null;
import ru.vood.core.runtime.type.Number;
import ru.vood.core.runtime.type.Varchar2;

public class QueryTable extends IndexByTable<Number, Varchar2> {

    public QueryTable() {
        super((new TableTraits<Number, Varchar2>(Null.toNumber())));
    }

    protected QueryTable(TableTraits<Number, Varchar2> traits) {
        super((new TableTraits<Number, Varchar2>(Null.toNumber())));
    }

    public QueryTable(QueryTable other) {
        super(new IndexByTable.TableTraits<Number, Varchar2>(Null.toNumber()));
        this.assign(other);
    }

    // // TODO: 12.09.2016 ----------------------------------------------------------- Удалить toString
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        Number key = this.first();
        while (!key.isNull_booleanValue()) {
            sb.append(this.get(key).getValue() + "\n \n");
            key = this.next(key);
        }
        System.out.println(QueryTable.class + "\n " + sb.toString());
        System.out.println("----------------------");
        return null;
    }

    @Override
    protected Varchar2 createValue() {
        return new Varchar2();
    }

    /**
     * добавляет в хвост текущего списка запросы из senderQueryTable
     *
     * @param senderQueryTable добавляемый массив запросов
     * @return объдиненный список запросов
     */
    public QueryTable queryTAddToQueryT(QueryTable senderQueryTable) {
        if (senderQueryTable == null) {
            return this;
        }
        Number key = senderQueryTable.first();
        while (!key.isNull_booleanValue()) {
            this.get(this.count().add(1), true).assign(senderQueryTable.get(key));
            key = senderQueryTable.next(key);
        }
        return this;
    }

    public IndexByTable<Number, Varchar2> copy() {
        return new QueryTable(this);
    }
}
