package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.abstr.StepsEditServise;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.abstr.TuneChainStepsEditService;

@Component
public class TuneChainStepsEdit extends TuneChainStepsEditService {

    @Autowired
    @Qualifier("editTableImpl")
    private StepsEditServise table;

    public void runChain(Object bdobjOld, Object bdobjNew) {
        // Вызов первого, остальное пойдет по цепочке
        QueryTableNew queryTable = table.runSteps((VBdObjectEntity) bdobjOld, (VBdObjectEntity) bdobjNew);
        chainStepsCreate.runChain(queryTable);
    }
}
