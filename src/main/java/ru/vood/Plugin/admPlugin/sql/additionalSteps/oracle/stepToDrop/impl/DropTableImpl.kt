package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToDrop.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.sql.QueryTableNew
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateAndDropServise
import ru.vood.Plugin.admPlugin.tune.PluginTunes

@Component
class DropTableImpl : StepsCreateAndDropServise {

    @Autowired
    private lateinit var tunes: PluginTunes

//    @Autowired
//    @Qualifier("addColomnImpl")
//    private lateinit var nextStep: StepsCreateAndDropServise


    override fun createDDL(bdObject: VBdObjectEntity): QueryTableNew {
        val tab = bdObject as? VBdTableEntity ?: return QueryTableNew()
        val queryTable = QueryTableNew()
        queryTable.add("drop table ${tunes.owner}.${tunes.prefixTable}${tab.code}")
        return queryTable
    }

//    override fun getNextStep(): StepsCreateAndDropServise {
//        return nextStep
//    }
}