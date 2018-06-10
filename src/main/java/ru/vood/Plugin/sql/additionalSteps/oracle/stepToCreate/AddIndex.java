package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.db.QueryTable;


@Deprecated
public class AddIndex extends StepsCreate {

    /**
     * @param bdObject - объкет по которому генерируется запрос
     */
    public AddIndex(Object bdObject) {
        super(bdObject);
    }

    /**
     * @param bdObject         - объкет по которому генерируется запрос
     * @param bdObjectIndirect - объкект необходимый для создания запроса, например для созданий внешнего ключа нужна вторая таблица к которой устанавливается связь
     */
    public AddIndex(Object bdObject, Object bdObjectIndirect) {
        super(bdObject, bdObjectIndirect);
    }

    /**
     * @param bdObject         - объкет по которому генерируется запрос
     * @param bdObjectIndirect - объкект необходимый для создания запроса, например для созданий внешнего ключа нужна вторая таблица к которой устанавливается связь
     * @param adds             - массив наименований столбцов для индекса
     */
    public AddIndex(Object bdObject, Object bdObjectIndirect, String... adds) {
        super(bdObject, bdObjectIndirect, adds);
    }

    @Override
    QueryTable additionOne(QueryTable queryTable) {
        if (!(bdObject instanceof VBdIndexEntity)) {
            return queryTable;
        }

        VBdIndexEntity bdIndex = (VBdIndexEntity) bdObject;
       /* StringBuffer stringBuffer = new StringBuffer();


        TableDBToJavaClass tableDBToJavaClass = TableDBToJavaClass.gettToJc(bdIndex);

        stringBuffer.append("alter table " + tableDBToJavaClass.getDbTable() + "\n");
        stringBuffer.append("add constraint PK#" + tableDBToJavaClass.getDbTable() + "_ID primary key (" + tableDBToJavaClass.getIdColomn().getDbField() + ")\n");
        stringBuffer.append(" using index tablespace \n" + AppConst.getTune(ListTunes.TABLE_SPASE_USER_INDEX) + AppConst.getTune(ListTunes.STORAGE_INDEX));
        queryTable.set(queryTable.count().add(1), new Varchar2(stringBuffer.toString()));*/

        return queryTable;
    }
}
