package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToDrop.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.sql.QueryTableNew
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateAndDropServise
import ru.vood.Plugin.admPlugin.tune.PluginTunes

@Component
class DropColumnImpl : StepsCreateAndDropServise {

    @Autowired
    private lateinit var tunes: PluginTunes

//    @Autowired
//    @Qualifier("dropColumnImpl")
//    private lateinit var nextStep: StepsCreateAndDropServise


    override fun createDDL(bdObject: VBdObjectEntity): QueryTableNew {
        val col = bdObject as? VBdColomnsEntity ?: return QueryTableNew()
        val queryTable = QueryTableNew()
        queryTable.add("ALTER TABLE ${tunes.owner}.${tunes.prefixTable}${col.parent.code} DROP COLUMN ${col.code}")
        return queryTable
    }
}