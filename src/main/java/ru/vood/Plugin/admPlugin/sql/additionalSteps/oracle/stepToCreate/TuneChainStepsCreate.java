package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateAndDropServise;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.TuneChainStepsCreateServise;


@Service
public class TuneChainStepsCreate extends TuneChainStepsCreateServise {

    @Autowired
    @Qualifier("addTableImpl")
    private StepsCreateAndDropServise table;

    public void runChain(VBdObjectEntity bdobj) {
        // Вызов первого, остальное пойдет по цепочке
        QueryTableNew queryTable = null;
        queryTable = table.runSteps(bdobj);
        runQueryes(queryTable);
    }
}
