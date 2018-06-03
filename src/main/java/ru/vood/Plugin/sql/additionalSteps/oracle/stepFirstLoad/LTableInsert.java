package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.spring.referenceBook.Tables;
import ru.vood.Plugin.db.QueryTable;

public class LTableInsert extends StepsFirstLoad {

    public QueryTable additionOneTEMP(QueryTable queryTable) {
        return additionOne(queryTable);
    }

    QueryTable additionOne(QueryTable queryTable) {

        VBdObjectTypeEntity bdObjType_TABLE = ObjectTypes.getDATE();

        VBdObjectEntity bdObj_DATE = Tables.getOBJECT();

        VBdTableEntity bdObject_date_table = new VBdTableEntity();
        bdObject_date_table.setCode("DATE");
        bdObject_date_table.setName("Дата");
        bdObject_date_table.setParent(bdObj_DATE);
        bdObject_date_table.setTypeObject(bdObjType_TABLE);
        bdObject_date_table.setJavaClass(VBdTableEntity.class.toString());

        VBdTableEntityService colomnsEntityService = LoadedCTX.getService(VBdTableEntityService.class);
        VBdTableEntity newTableEntity = (VBdTableEntity) colomnsEntityService.save(bdObject_date_table);

        return null;
    }
}
