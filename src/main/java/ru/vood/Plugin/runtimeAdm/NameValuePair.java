package ru.vood.Plugin.runtimeAdm;

import ru.vood.core.runtime.type.IndexByTable;
import ru.vood.core.runtime.type.Null;
import ru.vood.core.runtime.type.Varchar2;

import java.math.BigDecimal;
import java.util.Date;

public class NameValuePair extends IndexByTable<Varchar2, Varchar2> {


    public NameValuePair() {
        super(new IndexByTable.TableTraits<Varchar2, Varchar2>(Null.toVarchar2()));
    }

    public NameValuePair(Varchar2 name, Varchar2 val) {
        this();
        this.set(name, val);
    }

    public NameValuePair(String name, String val) {
        this(new Varchar2(name), new Varchar2(val));
    }

    public NameValuePair(NameValuePair other) {
        super(new IndexByTable.TableTraits<Varchar2, Varchar2>(Null.toVarchar2()));
        this.assign(other);
    }

    @Override
    public IndexByTable<Varchar2, Varchar2> copy() {
        return new NameValuePair(this);
    }

    @Override
    protected Varchar2 createValue() {
        return new Varchar2();
    }


    public Varchar2 set(Varchar2 key, Date value) {
        return super.set(key, new Varchar2(value.toString()));
    }

    public Varchar2 set(Varchar2 key, BigDecimal value) {
        return super.set(key, new Varchar2(value.toString()));
    }

}

