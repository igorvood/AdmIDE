package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectTypeEntityService;
import ru.vood.Plugin.db.QueryTable;

import java.util.List;

public class LTableInsert extends StepsFirstLoad {

    public QueryTable additionOneTEMP(QueryTable queryTable) {
        return additionOne(queryTable);
    }

    QueryTable additionOne(QueryTable queryTable) {

        VBdObjectTypeEntity bdObjType_TABLE = new VBdObjectTypeEntity();
        VBdObjectTypeEntityService vBdObjectTypeEntityService = bdObjType_TABLE.getServise();
        bdObjType_TABLE = vBdObjectTypeEntityService.findByCode("DATE");

        VBdObjectEntity bdObj_DATE = new VBdObjectEntity();
        VBdObjectEntityService vBdObjectEntityService = bdObj_DATE.getServise();

        List<VBdObjectEntity> vBdObjectEntityList = vBdObjectEntityService.findByCode("OBJECT");

        vBdObjectEntityList = vBdObjectEntityService.findByCodeAndTypeObjectCodeAndParent("DATE", "DATE", vBdObjectEntityList.get(0));
        bdObj_DATE = vBdObjectEntityList.get(0);

        VBdTableEntity bdObject_date_table = new VBdTableEntity();
        bdObject_date_table.setCode("DATE");
        bdObject_date_table.setName("Дата");
        bdObject_date_table.setParent(bdObj_DATE);
        bdObject_date_table.setTypeObject(bdObjType_TABLE);
        bdObject_date_table.setJavaClass(VBdTableEntity.class.toString());
        bdObject_date_table.save();

        return null;
    }
}
