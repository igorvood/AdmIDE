package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToDrop.abstr

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.ChainQueryServise

abstract class TuneChainStepsDropService : ChainQueryServise() {

    abstract fun runChain(bdobj: VBdObjectEntity)

}