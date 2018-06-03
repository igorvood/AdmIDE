package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectTypeEntityService;
import ru.vood.Plugin.db.QueryTable;

public class LObjTypeInsert extends StepsFirstLoad {
    QueryTable additionOne(QueryTable queryTable) {

        VBdObjectTypeEntityService VBdObjectTypeEntityService = LoadedCTX.getService(VBdObjectTypeEntityService.class);

        VBdObjectTypeEntity bdObjType_TYPE = new VBdObjectTypeEntity();
        bdObjType_TYPE.setCode("TABLE");
        bdObjType_TYPE.setName("Таблица");
        bdObjType_TYPE = (VBdObjectTypeEntity) VBdObjectTypeEntityService.save(bdObjType_TYPE);


        VBdObjectTypeEntity bdObjType_REFERENCE = new VBdObjectTypeEntity();
        bdObjType_REFERENCE.setCode("REFERENCE");
        bdObjType_REFERENCE.setName("Ссылка");
        bdObjType_REFERENCE = (VBdObjectTypeEntity) VBdObjectTypeEntityService.save(bdObjType_REFERENCE);

        VBdObjectTypeEntity bdObjType_ARRAY = new VBdObjectTypeEntity();
        bdObjType_ARRAY.setCode("ARRAY");
        bdObjType_ARRAY.setName("Массив");
        bdObjType_ARRAY = (VBdObjectTypeEntity) VBdObjectTypeEntityService.save(bdObjType_ARRAY);

        VBdObjectTypeEntity bdObjType_STRING = new VBdObjectTypeEntity();
        bdObjType_STRING.setCode("STRING");
        bdObjType_STRING.setName("Строка");
        bdObjType_STRING = (VBdObjectTypeEntity) VBdObjectTypeEntityService.save(bdObjType_STRING);

        VBdObjectTypeEntity bdObjType_NUMBER = new VBdObjectTypeEntity();
        bdObjType_NUMBER.setCode("NUMBER");
        bdObjType_NUMBER.setName("Число");
        bdObjType_NUMBER = (VBdObjectTypeEntity) VBdObjectTypeEntityService.save(bdObjType_NUMBER);

        VBdObjectTypeEntity bdObjType_DATE = new VBdObjectTypeEntity();
        bdObjType_DATE.setCode("DATE");
        bdObjType_DATE.setName("Дата");
        bdObjType_DATE = (VBdObjectTypeEntity) VBdObjectTypeEntityService.save(bdObjType_DATE);

        VBdObjectTypeEntity bdObjType_BOOL = new VBdObjectTypeEntity();
        bdObjType_BOOL.setCode("BOOLEAN");
        bdObjType_BOOL.setName("Логика");
        bdObjType_BOOL = (VBdObjectTypeEntity) VBdObjectTypeEntityService.save(bdObjType_BOOL);

        VBdObjectTypeEntity bdObjType_colomn = new VBdObjectTypeEntity();
        bdObjType_colomn.setCode("COLOMN");
        bdObjType_colomn.setName("Колонка таблицы");
        bdObjType_colomn.setParent(bdObjType_TYPE);
        bdObjType_colomn = (VBdObjectTypeEntity) VBdObjectTypeEntityService.save(bdObjType_colomn);

        return null;
    }
}
