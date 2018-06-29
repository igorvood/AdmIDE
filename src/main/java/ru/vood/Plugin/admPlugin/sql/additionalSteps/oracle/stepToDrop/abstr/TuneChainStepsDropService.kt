package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToDrop.abstr

import org.springframework.beans.factory.annotation.Autowired
import ru.vood.Plugin.admPlugin.sql.QueryTableNew
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.TuneChainStepsCreateServise

abstract class TuneChainStepsDropService {

    @Autowired
    protected lateinit var tuneChainStepsCreateServise: TuneChainStepsCreateServise

    abstract fun runChain(bdobj: Any)

    protected fun runChain(queryTable: QueryTableNew) {
        tuneChainStepsCreateServise.runChain(queryTable)
    }


}