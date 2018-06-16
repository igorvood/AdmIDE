package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;


@Component
public class AddTableImpl implements StepsCreateServise {

    @Autowired
    AddPrimaryKeyImpl primaryKey;

    @Autowired
    AddForeignKeyForParentImpl foreignKeyForParent;

    @Autowired
    @Qualifier("addColomnImpl")
    private StepsCreateServise nextStep;

    @Autowired
    private PluginTunes tunes;

//    @Autowired
//    @Qualifier("jpaVBdColomnsEntityService")
//    private VBdColomnsEntityService colomnsEntityService;

    @Override
    public QueryTableNew createDDL(VBdObjectEntity bdObject) {
        if (!(bdObject instanceof VBdTableEntity)) {
            return null;
        }

        QueryTableNew queryTable = new QueryTableNew();

        VBdTableEntity bdTable = (VBdTableEntity) bdObject;
        if (bdTable.getTypeObject().equals(ObjectTypes.getTABLE())) {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("-- Create table\n");
            stringBuffer.append("create table " + tunes.getOwner() + "." + tunes.getPrefixTable() + bdTable.getCode() + "\n");
            stringBuffer.append("(id NUMBER not null) ");
            stringBuffer.append(" tablespace " + tunes.getTableSpaseUserTable() + "\n ");
            stringBuffer.append(tunes.getStorageTable() + "\n");
            queryTable.add(stringBuffer.toString());

            //Добавление первичного ключа
            queryTable.addAll(primaryKey.createDDL(bdTable));

            // Автоматически добавить ID в колонки
//            VBdColomnsEntity colomnsEntity = new VBdColomnsEntity();
//            colomnsEntity.setParent(bdObject);
//            colomnsEntity.setCode("ID");
//            colomnsEntity.setName("ID");
//            colomnsEntity.setNotNull("1");
//            colomnsEntity.setTypeColomn(ObjectTypes.getNUMBER());
//            colomnsEntity.setTypeValue(Tables.getAny("NUM"));
//            colomnsEntity = colomnsEntityService.save(colomnsEntity);


            //если добавляем таблицу с настоящим родителем, то надо их связать внешним ключем
            if (bdTable.getParent() instanceof VBdTableEntity) {
                queryTable.addAll(foreignKeyForParent.createDDL(bdTable));
            }
        }

        return queryTable;
    }

    @Override
    public StepsCreateServise getNextStep() {
        return nextStep;
    }

}
