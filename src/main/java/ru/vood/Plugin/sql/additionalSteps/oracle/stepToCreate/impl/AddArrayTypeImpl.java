package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.QueryTableNew;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;
import ru.vood.Plugin.sql.dbms.oracle.AddIndexSql;
import ru.vood.Plugin.sql.sqlFactory.SQLFactory;

@Component
public class AddArrayTypeImpl implements StepsCreateServise {

    @Autowired
    private PluginTunes pluginTunes;

    @Autowired
    private AddIndexSql addIndexSql;

    @Override
    public QueryTableNew createDDL(VBdObjectEntity bdObject) {
        if (!(bdObject instanceof VBdTableEntity)) {
            return null;
        }


        VBdTableEntity bdTable = (VBdTableEntity) bdObject;
        QueryTableNew queryTable = null;
        if (bdTable.getTypeObject().equals(ObjectTypes.getARRAY())) {
            queryTable = new QueryTableNew();


            String tmp = SQLFactory.getInstance().getSQLForAddCollectionId(bdTable.getToType().getCode());
            queryTable.add(tmp);

            tmp = addIndexSql.generateSys(pluginTunes.getPrefixTable() + bdTable.getToType().getCode(), "COLLECTIONID");
            queryTable.add(tmp);


        }

        return queryTable;
    }

    @Override
    public StepsCreateServise getNextStep() {
        return null;
    }
}
