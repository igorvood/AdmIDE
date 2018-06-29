package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToDrop

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateAndDropServise
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToDrop.abstr.TuneChainStepsDropService

@Service
class TuneChainStepsDrop : TuneChainStepsDropService() {
    @Autowired
    @Qualifier("dropTableImpl")
    private lateinit var table: StepsCreateAndDropServise

    override fun runChain(bdobj: Any) {
        // Вызов первого, остальное пойдет по цепочке

        try {
            val queryTable = table.runSteps(bdobj as VBdObjectEntity)
            runChain(queryTable)
        } catch (e: Exception) {

        }

    }

}