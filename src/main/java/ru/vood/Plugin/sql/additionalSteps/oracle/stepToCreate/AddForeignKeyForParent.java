package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.applicationConst.TypeObject;
import ru.vood.Plugin.db.QueryTable;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.core.runtime.type.Varchar2;

//import ru.vood.Plugin.entity.bdEntity.BDTable;

public class AddForeignKeyForParent extends StepsCreate {
    /**
     * @param bdObject - объкет по которому генерируется запрос
     */
    public AddForeignKeyForParent(Object bdObject) {
        super(bdObject);
    }

    @Override
    QueryTable additionOne(QueryTable queryTable) {
        if (!(bdObject instanceof VBdTableEntity)) {
            return queryTable;
        }

        if (queryTable == null) {
            queryTable = new QueryTable();
        }
        VBdTableEntity bdTable = (VBdTableEntity) bdObject;
        if (bdTable.getParent() != null && !bdTable.getParent().getCode().equals("TABLE") && bdTable.getTypeObject().getCode().equals(TypeObject.TABLE.getName())) {
            String pref = AppConst.getTune(ListTunes.PREFIX_TABLE);
            String forKey = AddConstraintSql.getSql(pref + bdTable.getCode(), "ID", pref + bdTable.getParent().getCode(), "ID");
            queryTable.set(queryTable.count().add(1), new Varchar2(forKey));
        }

        return queryTable;
    }
}
