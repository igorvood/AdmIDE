package ru.vood.Plugin.sql.dbms.oracle;

import ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad.TuneChainStepsFirstLoad;
import ru.vood.Plugin.sql.sqlInterfaces.SQLFistrLoadShemeInterface;


public class SQLFistrLoadShemeOra implements SQLFistrLoadShemeInterface {

    @Override
    public void getSQL() {
        TuneChainStepsFirstLoad.runChain();
    }
}
