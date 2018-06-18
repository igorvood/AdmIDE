package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.TuneChainStepsCreateServise;

@Service
public class TuneChainStepsCreate extends TuneChainStepsCreateServise {

    @Autowired
    @Qualifier("addArrayImpl")
    private StepsCreateServise table;

    public void runChain(Object bdobj) {
        // Вызов первого, остальное пойдет по цепочке
        QueryTableNew queryTable = null;
        try {
            queryTable = table.runSteps((VBdObjectEntity) bdobj);
            runChain(queryTable);
        } catch (Exception e) {

        }
    }
}
