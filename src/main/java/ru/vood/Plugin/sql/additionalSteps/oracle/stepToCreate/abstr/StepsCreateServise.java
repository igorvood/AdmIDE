package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.abstr;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.QueryTableNew;

public interface StepsCreateServise {

    QueryTableNew createDDL(VBdObjectEntity bdObject);

    default QueryTableNew runStep(VBdObjectEntity bdObject) {
        QueryTableNew queryTable = new QueryTableNew();

        queryTable.addAll(createDDL(bdObject));
        if (getNextStep() != null) {
            queryTable.addAll(getNextStep().runStep(bdObject));
        }
        return queryTable;
    }

    default StepsCreateServise getNextStep() {
        return null;
    }

    ;

}