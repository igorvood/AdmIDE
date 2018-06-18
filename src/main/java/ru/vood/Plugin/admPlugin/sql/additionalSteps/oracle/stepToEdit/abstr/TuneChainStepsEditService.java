package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.abstr;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.TuneChainStepsCreateServise;

public abstract class TuneChainStepsEditService {

    @Autowired
    protected TuneChainStepsCreateServise chainStepsCreate;

}
