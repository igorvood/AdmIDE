package ru.vood.Plugin.admPlugin.generateCode.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenAnnotationClassServiceKT
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.addingImport.AddAnnotationClass
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.addingImport.ParamOfAnnotation
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.tune.PluginTunes
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.Table

@Component
class GenAnnotationClassImplKT : GenAnnotationClassServiceKT {

    @Autowired
    private lateinit var genCodeCommonFunction: GenCodeCommonFunctionKT

    @Autowired
    private lateinit var addAnnotationClass: AddAnnotationClass

    @Autowired
    private lateinit var pluginTunes: PluginTunes

    override fun genCode(entity: VBdTableEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        val code = StringBuilder("")
        if (typeOfGenClass == TypeOfGenClassKT.ENTITY_CLASS) code.append(genCodeEntity(entity))
        return code
    }

    private fun genCodeEntity(entity: VBdTableEntity): StringBuilder {
        val code = StringBuilder()
        code.append(addAnnotationClass.getCode(Entity::class.java))

        val paramOfAnnotation = ParamOfAnnotation()
        paramOfAnnotation.put("name", "\"${genCodeCommonFunction.getTableName(entity)}\"")
        paramOfAnnotation.put("schema", "\"${pluginTunes.owner}\"")

        code.append(addAnnotationClass.getCode(Table::class.java, paramOfAnnotation))

        if (genCodeCommonFunction.isRootEntity(entity, TypeOfGenClassKT.ENTITY_CLASS)) {
            paramOfAnnotation.clear()
            paramOfAnnotation.put("strategy", "InheritanceType.JOINED")
            addAnnotationClass.getCode(InheritanceType::class.java)
            code.append(addAnnotationClass.getCode(Inheritance::class.java, paramOfAnnotation))
        }
        return code
    }

}