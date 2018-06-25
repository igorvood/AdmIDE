package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.GenCodeCommonFunctionKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenClassBodyServiceKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenFieldsServiceKT
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService

@Component
class GenClassBodyImplKT : GenClassBodyServiceKT {

    @Autowired
    private lateinit var genCodeCommonFunction: GenCodeCommonFunctionKT

    @Autowired
    private lateinit var colomnsEntityService: VBdColomnsEntityService

    @Autowired
    private lateinit var genFieldsService: GenFieldsServiceKT


    private fun genCodeEntiy(entity: VBdObjectEntity): StringBuilder {
        val code = StringBuilder()
        if (genCodeCommonFunction.isRootEntity(entity)) {
            code.append(genCodeCommonFunction.getIdField())
        }

        val colomnsEntities = colomnsEntityService.findByParent(entity as VBdTableEntity)

        for (colomn in colomnsEntities) {
            code.append(genFieldsService.genCode(colomn, TypeOfGenClassKT.ENTITY_CLASS))
        }

        return code
    }

    @JvmOverloads
    override fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        val code = StringBuilder()
        return if (typeOfGenClass == TypeOfGenClassKT.ENTITY_CLASS) genCodeEntiy(entity) else code
    }


}