package ru.vood.Plugin.sql.dbms.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad.TuneChainStepsFirstLoad;
import ru.vood.Plugin.sql.sqlInterfaces.SQLFistrLoadShemeInterface;

@Component
public class SQLFistrLoadShemeOra implements SQLFistrLoadShemeInterface {

    @Autowired
    private TuneChainStepsFirstLoad runChain;

    @Override
    public void getSQL() {
        runChain.run();
    }
}
