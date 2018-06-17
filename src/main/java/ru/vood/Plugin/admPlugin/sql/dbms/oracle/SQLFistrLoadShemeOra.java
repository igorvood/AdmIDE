package ru.vood.Plugin.admPlugin.sql.dbms.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.except.CoreExeption;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepFirstLoad.TuneChainStepsFirstLoad;
import ru.vood.Plugin.admPlugin.sql.sqlInterfaces.SQLFistrLoadShemeInterface;

@Component
public class SQLFistrLoadShemeOra implements SQLFistrLoadShemeInterface {

    @Autowired
    private TuneChainStepsFirstLoad runChain;

    @Override
    public void getSQL() throws CoreExeption {
        runChain.run();
    }
}
