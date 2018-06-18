package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.impl.AddIndexImpl;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.abstr.StepsEditServise;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Component
public class EditIndexImpl implements StepsEditServise {

    @Autowired
    private PluginTunes tunes;

    @Autowired
    private AddIndexImpl addIndex;

    @Override
    public QueryTableNew editDDL(VBdObjectEntity bdObjectOld, VBdObjectEntity bdObjectNew) {
        if (!(bdObjectNew instanceof VBdIndexEntity) || !(bdObjectOld instanceof VBdIndexEntity)) {
            return null;
        }

        QueryTableNew queryTable = new QueryTableNew();
        VBdIndexEntity bdIndexOld = (VBdIndexEntity) bdObjectOld;
        VBdIndexEntity bdIndexNew = (VBdIndexEntity) bdObjectNew;
        if (bdIndexNew.getColomnsEntities() != null) {
            if (!bdIndexOld.getCode().equals(bdIndexNew.getCode())) {
                if (bdIndexOld.getColomnsEntities().size() != bdIndexNew.getColomnsEntities().size()) {

                    queryTable.add("DROP INDEX " + bdIndexOld.getCode());
                    queryTable.addAll(addIndex.createDDL(bdIndexNew));
                }
            }
        }
        return queryTable;
    }

}
