package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import ru.vood.Plugin.db.QueryTable;

//import ru.vood.Plugin.entity.bdEntity.BDTable;

@Deprecated
public class AddArrayType extends StepsCreate {

    /**
     * @param bdObject - объкет по которому генерируется запрос
     */
    public AddArrayType(Object bdObject) {
        super(bdObject);
    }

    @Override
    QueryTable additionOne(QueryTable queryTable) {
  /*      if (!(bdObject instanceof VBdTableEntity)) {
            return queryTable;
        }
        VBdTableEntity bdTable = (VBdTableEntity) bdObject;
        if ((bdTable.getTypeObject().getCode().equals(TypeObject.ARRAY.getName()))) {
            StringBuffer stringBuffer = new StringBuffer();
            String tmp = SQLFactory.getInstance().getSQLForAddCollectionId(bdTable.getToType().getCode());

            queryTable.set(queryTable.count().add(1), new Varchar2(tmp));

            tmp = AddIndexSql.generateSys(AppConst.getTune(ListTunes.PREFIX_TABLE) + bdTable.getToType().getCode(), "COLLECTIONID");
            queryTable.set(queryTable.count().add(1), new Varchar2(tmp));


        }*/

        return queryTable;
    }
}
