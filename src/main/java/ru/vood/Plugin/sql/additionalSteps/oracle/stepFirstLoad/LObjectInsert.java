package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectTypeEntityService;
import ru.vood.Plugin.db.QueryTable;

public class LObjectInsert extends StepsFirstLoad {
    QueryTable additionOne(QueryTable queryTable) {


        VBdObjectTypeEntity bdObjType_TABLE = new VBdObjectTypeEntity();
        VBdObjectTypeEntityService vBdObjectTypeEntityService = bdObjType_TABLE.getServise();
        bdObjType_TABLE = vBdObjectTypeEntityService.findByCode("TABLE");

        VBdObjectEntity bdObject_obj = new VBdObjectEntity();
        bdObject_obj.setCode("OBJECT");
        bdObject_obj.setName("Объекты");
        bdObject_obj.setParent(null);
        bdObject_obj.setTypeObject(bdObjType_TABLE);
        bdObject_obj.setJavaClass(VBdObjectEntity.class.toString());
        bdObject_obj.save();

        VBdObjectTypeEntity bdObjType_DATE = vBdObjectTypeEntityService.findByCode("DATE");

        VBdObjectEntity bdObject_date = new VBdObjectEntity();
        bdObject_date.setCode("DATE");
        bdObject_date.setName("Даты");
        bdObject_date.setParent(bdObject_obj);
        bdObject_date.setTypeObject(bdObjType_DATE);
        bdObject_date.setJavaClass(VBdObjectEntity.class.toString());
        bdObject_date.save();

        VBdObjectTypeEntity bdObjType_REFERENCE = vBdObjectTypeEntityService.findByCode("REFERENCE");

        VBdObjectEntity bdObject_REFERENCE = new VBdObjectEntity();
        bdObject_REFERENCE.setCode("REFERENCE");
        bdObject_REFERENCE.setName("Ссылки");
        bdObject_REFERENCE.setParent(bdObject_obj);
        bdObject_REFERENCE.setTypeObject(bdObjType_REFERENCE);
        bdObject_REFERENCE.setJavaClass(VBdObjectEntity.class.toString());
        bdObject_REFERENCE.save();

        VBdObjectTypeEntity bdObjType_ARRAY = vBdObjectTypeEntityService.findByCode("ARRAY");

        VBdObjectEntity bdObject_ARRAY = new VBdObjectEntity();
        bdObject_ARRAY.setCode("ARRAY");
        bdObject_ARRAY.setName("Массивы");
        bdObject_ARRAY.setParent(bdObject_obj);
        bdObject_ARRAY.setTypeObject(bdObjType_ARRAY);
        bdObject_ARRAY.setJavaClass(VBdObjectEntity.class.toString());
        bdObject_ARRAY.save();

        VBdObjectTypeEntity bdObjType_STRING = vBdObjectTypeEntityService.findByCode("STRING");

        VBdObjectEntity bdObject_STRING = new VBdObjectEntity();
        bdObject_STRING.setCode("STRING");
        bdObject_STRING.setName("Строки");
        bdObject_STRING.setParent(bdObject_obj);
        bdObject_STRING.setTypeObject(bdObjType_STRING);
        bdObject_STRING.setJavaClass(VBdObjectEntity.class.toString());
        bdObject_STRING.save();

        VBdObjectTypeEntity bdObjType_NUMBER = vBdObjectTypeEntityService.findByCode("NUMBER");

        VBdObjectEntity bdObject_NUMBER = new VBdObjectEntity();
        bdObject_NUMBER.setCode("NUMBER");
        bdObject_NUMBER.setName("Числа");
        bdObject_NUMBER.setParent(bdObject_obj);
        bdObject_NUMBER.setTypeObject(bdObjType_NUMBER);
        bdObject_NUMBER.setJavaClass(VBdObjectEntity.class.toString());
        bdObject_NUMBER.save();

        VBdObjectEntity bdObject_table = new VBdObjectEntity();
        bdObject_table.setCode("TABLE");
        bdObject_table.setName("Таблицы");
        bdObject_table.setParent(bdObject_obj);
        bdObject_table.setTypeObject(bdObjType_TABLE);
        bdObject_table.setJavaClass(VBdObjectEntity.class.toString());
        bdObject_table.save();

        return null;
    }
}
