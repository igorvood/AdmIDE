package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.GenCodeCommonFunctionKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenAnnotationClassServiceKT
import ru.vood.Plugin.admPlugin.tune.PluginTunes

@Component
class GenAnnotationClassImplKT : GenAnnotationClassServiceKT {

    @Autowired
    private lateinit var genCodeCommonFunction: GenCodeCommonFunctionKT

    @Autowired
    private lateinit var pluginTunes: PluginTunes

    override fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        val code = StringBuilder("")
        if (typeOfGenClass == TypeOfGenClassKT.ENTITY_CLASS) code.append(genCodeEntity(entity as VBdTableEntity))
        return code
    }

    private fun genCodeEntity(entity: VBdTableEntity): StringBuilder {
        val code = StringBuilder()
        code.append("@Entity\n")
        code.append("@Table(name = \"" + genCodeCommonFunction.getTableName(entity) + "\" , schema = \"" + pluginTunes.owner + "\")\n")
        if (genCodeCommonFunction.isRootEntity(entity, TypeOfGenClassKT.ENTITY_CLASS)) {
            code.append("@Inheritance(strategy = InheritanceType.JOINED)\n")
        }
        return code
    }


}