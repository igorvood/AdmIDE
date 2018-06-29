package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;

public interface StepsCreateAndDropServise {

    QueryTableNew createDDL(VBdObjectEntity bdObject);

    default QueryTableNew runSteps(VBdObjectEntity bdObject) {
        QueryTableNew queryTable = new QueryTableNew();

        QueryTableNew queryTabletmp = createDDL(bdObject);
        if (queryTabletmp != null) {
            queryTable.addAll(queryTabletmp);
        }
        if (getNextStep() != null) {
            queryTable.addAll(getNextStep().runSteps(bdObject));
        }
        return queryTable;
    }

    default StepsCreateAndDropServise getNextStep() {
        return null;
    }

}
