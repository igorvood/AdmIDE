package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.spring.referenceBook.Tables;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.sql.QueryTableNew;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;


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

            //если добавляем таблицу с настоящим родителем, то надо их связать внешним ключем
            if (!bdTable.getParent().equals(Tables.getTABLE())) {
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
