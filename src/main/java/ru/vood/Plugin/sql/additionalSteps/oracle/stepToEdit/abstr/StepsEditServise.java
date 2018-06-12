package ru.vood.Plugin.sql.additionalSteps.oracle.stepToEdit.abstr;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.sql.QueryTableNew;

public interface StepsEditServise {

    QueryTableNew editDDL(VBdObjectEntity bdObjectOld, VBdObjectEntity bdObjectNew);

    default QueryTableNew runSteps(VBdObjectEntity bdObjectOld, VBdObjectEntity bdObjectNew) {
        QueryTableNew queryTable = new QueryTableNew();

        QueryTableNew queryTabletmp = editDDL(bdObjectOld, bdObjectNew);
        if (queryTabletmp != null) {
            queryTable.addAll(queryTabletmp);
        }
        if (getNextStep() != null) {
            queryTable.addAll(getNextStep().runSteps(bdObjectOld, bdObjectNew));
        }
        return queryTable;
    }

    default StepsEditServise getNextStep() {
        return null;
    }
}
