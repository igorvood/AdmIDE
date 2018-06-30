package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.ChainQueryServise;

public abstract class TuneChainStepsCreateServise extends ChainQueryServise {
    public abstract void runChain(VBdObjectEntity bdobj);
}
