package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.Refactoring.Query;
import ru.vood.Plugin.db.QueryTable;

abstract public class StepsFirstLoad {
    StepsFirstLoad nextStep;

    public void setNextStep(StepsFirstLoad nextStep) {
        this.nextStep = nextStep;
    }

    public void addition() {

        QueryTable table = new QueryTable();
        table = additionOne(table);
        if (table != null) {
            Query.executeCreate(table);
        }

        if (this.nextStep != null) {
            this.nextStep.addition();
        }
    }

    abstract QueryTable additionOne(QueryTable queryTable);
}


