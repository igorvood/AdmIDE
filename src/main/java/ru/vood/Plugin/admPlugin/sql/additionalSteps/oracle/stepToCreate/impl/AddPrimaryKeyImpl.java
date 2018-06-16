package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddPrimaryKeySql;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Component
public class AddPrimaryKeyImpl implements StepsCreateServise {

/*    @Autowired
    @Qualifier("addColomnImpl")
    private StepsCreateServise nextStep;*/


    @Autowired
    private PluginTunes tunes;

    @Autowired
    private AddPrimaryKeySql primaryKeySql;

    public QueryTableNew createDDL(VBdObjectEntity bdObject) {

        if (!(bdObject instanceof VBdTableEntity)) {
            return null;
        }

        QueryTableNew queryTable = null;
        VBdTableEntity bdTable = (VBdTableEntity) bdObject;
        if (bdTable.getTypeObject().equals(ObjectTypes.getTABLE())) {
            queryTable = new QueryTableNew();
            queryTable.add(primaryKeySql.generateUserPK(tunes.getPrefixTable() + bdTable.getCode()));
        }
        return queryTable;

    }

    public StepsCreateServise getNextStep() {
        return null;
    }
}
