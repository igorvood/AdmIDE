package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import ru.vood.Plugin.admPlugin.entityHiber.VBdTableEntity;
import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.applicationConst.TypeObject;
import ru.vood.Plugin.db.QueryTable;
import ru.vood.Plugin.sql.dbms.oracle.AddPrimaryKeySql;
import ru.vood.Plugin.sql.dbms.oracle.LimitingDBMS;
import ru.vood.core.runtime.type.Varchar2;

public class AddPrimaryKey extends StepsCreate {


    /**
     * @param bdObject - объкет по которому генерируется запрос
     */
    public AddPrimaryKey(Object bdObject) {
        super(bdObject);
    }

    /**
     * @param bdObject         - объкет по которому генерируется запрос
     * @param bdObjectIndirect - объкект необходимый для создания запроса, например для созданий внешнего ключа нужна вторая таблица к которой устанавливается связь
     */
    public AddPrimaryKey(Object bdObject, Object bdObjectIndirect) {
        super(bdObject, bdObjectIndirect);
    }

    @Override
    QueryTable additionOne(QueryTable queryTable) {
        if (!(bdObject instanceof VBdTableEntity)) {
            return queryTable;
        }

        VBdTableEntity bdTable = (VBdTableEntity) bdObject;
        if (bdTable.getTypeObject().getCode().equals(TypeObject.TABLE.getName())) {
            queryTable.set(queryTable.count().add(1), new Varchar2(AddPrimaryKeySql.generateUserPK(LimitingDBMS.getNameObj(AppConst.getTune(ListTunes.PREFIX_TABLE) +
                    bdTable.getCode()))));
        }
        return queryTable;
    }
}
