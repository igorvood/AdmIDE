package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.abstr;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.sql.QueryTableNew;

public interface StepsCreateServise {

    QueryTableNew createDDL(VBdObjectEntity bdObject);

    default QueryTableNew runSteps(VBdObjectEntity bdObject) {
        QueryTableNew queryTable = new QueryTableNew();

        QueryTableNew queryTabletmp = createDDL(bdObject);
        if (queryTabletmp != null) {
            queryTable.addAll(createDDL(bdObject));
        }
        if (getNextStep() != null) {
            queryTable.addAll(getNextStep().runSteps(bdObject));
        }
        return queryTable;
    }

    default StepsCreateServise getNextStep() {
        return null;
    }

}
