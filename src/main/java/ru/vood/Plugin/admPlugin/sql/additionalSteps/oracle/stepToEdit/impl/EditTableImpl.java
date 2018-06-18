package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.abstr.StepsEditServise;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Component
public class EditTableImpl implements StepsEditServise {


    @Autowired
    @Qualifier("editColomnImpl")
    private StepsEditServise nextStep;

    @Autowired
    private PluginTunes tunes;

    @Override
    public QueryTableNew editDDL(VBdObjectEntity bdObjectOld, VBdObjectEntity bdObjectNew) {

        if (!(bdObjectOld instanceof VBdTableEntity) || !(bdObjectNew instanceof VBdTableEntity)) {
            return null;
        }

        QueryTableNew queryTable = new QueryTableNew();

        VBdTableEntity bdTableOld = (VBdTableEntity) bdObjectOld;
        VBdTableEntity bdTableNew = (VBdTableEntity) bdObjectNew;
        if (bdTableOld.getTypeObject().equals(ObjectTypes.getTABLE()) && bdTableNew.getTypeObject().equals(ObjectTypes.getTABLE())) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("alter table " + tunes.getOwner() + "." + tunes.getPrefixTable() + bdTableOld.getCode() + " rename to " + tunes.getPrefixTable() + bdTableNew.getCode());
            queryTable.add(stringBuffer.toString());
        }

        return queryTable;
    }

    @Override
    public StepsEditServise getNextStep() {
        return nextStep;
    }
}
