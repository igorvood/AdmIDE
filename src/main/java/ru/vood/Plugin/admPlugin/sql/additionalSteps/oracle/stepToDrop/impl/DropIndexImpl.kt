package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToDrop.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.sql.QueryTableNew
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateAndDropServise
import ru.vood.Plugin.admPlugin.tune.PluginTunes

@Component
class DropIndexImpl : StepsCreateAndDropServise {
    @Autowired
    private lateinit var tunes: PluginTunes

    override fun createDDL(bdObject: VBdObjectEntity): QueryTableNew {
        val index = bdObject as? VBdIndexEntity ?: return QueryTableNew()
        val queryTable = QueryTableNew()
        queryTable.add("DROP INDEX  ${tunes.owner}.${index.code}")
        return queryTable
    }

}