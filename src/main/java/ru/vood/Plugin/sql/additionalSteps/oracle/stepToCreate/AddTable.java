package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.applicationConst.TypeObject;
import ru.vood.Plugin.db.QueryTable;
import ru.vood.core.runtime.type.Varchar2;

@Deprecated
public class AddTable extends StepsCreate {


    /**
     * @param bdObject - объкет по которому генерируется запрос
     */
    public AddTable(Object bdObject) {
        super(bdObject);
    }

    /**
     * @param bdObject         - объкет по которому генерируется запрос
     * @param bdObjectIndirect - объкект необходимый для создания запроса, например для созданий внешнего ключа нужна вторая таблица к которой устанавливается связь
     */
    public AddTable(Object bdObject, Object bdObjectIndirect) {
        super(bdObject, bdObjectIndirect);
    }

    @Override
    QueryTable additionOne(QueryTable queryTable) {
        if (!(bdObject instanceof VBdTableEntity)) {
            return queryTable;
        }
        VBdTableEntity bdTable = (VBdTableEntity) bdObject;
        if (bdTable.getTypeObject().getCode().equals(TypeObject.TABLE.getName())) {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("-- Create table\n");
            stringBuffer.append("create table " + AppConst.getTune(ListTunes.PREFIX_TABLE) + bdTable.getCode() + "\n");
            stringBuffer.append("(id NUMBER not null) ");
            stringBuffer.append(" tablespace " + AppConst.getTune(ListTunes.TABLE_SPASE_USER_TABLE) + "\n ");
            stringBuffer.append(AppConst.getTune(ListTunes.STORAGE_TABLE) + "\n");
            queryTable.set(queryTable.count().add(1), new Varchar2(stringBuffer.toString()));


        }

        return queryTable;
    }
}
