package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.db.QueryTable;

public class LObjTypeInsert extends StepsFirstLoad {
    QueryTable additionOne(QueryTable queryTable) {
        VBdObjectTypeEntity bdObjType_TYPE = new VBdObjectTypeEntity();
        bdObjType_TYPE.setCode("TABLE");
        bdObjType_TYPE.setName("Таблица");
        bdObjType_TYPE.save();

        VBdObjectTypeEntity bdObjType_REFERENCE = new VBdObjectTypeEntity();
        bdObjType_REFERENCE.setCode("REFERENCE");
        bdObjType_REFERENCE.setName("Ссылка");
        bdObjType_REFERENCE.save();

        VBdObjectTypeEntity bdObjType_ARRAY = new VBdObjectTypeEntity();
        bdObjType_ARRAY.setCode("ARRAY");
        bdObjType_ARRAY.setName("Массив");
        bdObjType_ARRAY.save();

        VBdObjectTypeEntity bdObjType_STRING = new VBdObjectTypeEntity();
        bdObjType_STRING.setCode("STRING");
        bdObjType_STRING.setName("Строка");
        bdObjType_STRING.save();

        VBdObjectTypeEntity bdObjType_NUMBER = new VBdObjectTypeEntity();
        bdObjType_NUMBER.setCode("NUMBER");
        bdObjType_NUMBER.setName("Число");
        bdObjType_NUMBER.save();

        VBdObjectTypeEntity bdObjType_DATE = new VBdObjectTypeEntity();
        bdObjType_DATE.setCode("DATE");
        bdObjType_DATE.setName("Дата");
        bdObjType_DATE.save();

        VBdObjectTypeEntity bdObjType_BOOL = new VBdObjectTypeEntity();
        bdObjType_BOOL.setCode("BOOLEAN");
        bdObjType_BOOL.setName("Логика");
        bdObjType_BOOL.save();

        VBdObjectTypeEntity bdObjType_colomn = new VBdObjectTypeEntity();
        bdObjType_colomn.setCode("COLOMN");
        bdObjType_colomn.setName("Колонка таблицы");
        bdObjType_colomn.setParent(bdObjType_TYPE);
        bdObjType_colomn.save();

        return null;
    }
}
