package ru.vood.Plugin.entity.toXml;

import ru.vood.core.runtime.type.IndexByTable;
import ru.vood.core.runtime.type.Null;
import ru.vood.core.runtime.type.Varchar2;

/**
 * Created by igor on 27.06.2016.
 */
public class ObjectTIndexTable extends IndexByTable<Varchar2, ObjectT> {

    public ObjectTIndexTable() {
        super(new IndexByTable.TableTraits<Varchar2, ObjectT>(Null.toVarchar2()));
    }

    public ObjectTIndexTable(ObjectTIndexTable other) {
        super(new IndexByTable.TableTraits<Varchar2, ObjectT>(Null.toVarchar2()));
        this.assign(other);
    }

    protected ObjectT createValue() {
        return new ObjectT();
    }

    public IndexByTable<Varchar2, ObjectT> copy() {
        return new ObjectTIndexTable(this);
    }

    /*public class ObjectTForTable extends ObjectT implements ru.vood.core.runtime.type.i.Clonable<ObjectTForTable> {
        public ObjectTForTable copy() {
            return (ObjectTForTable) CopyObject.copy(this);
        }
    }*/
}
