package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.GenCodeCommonFunctionKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenAnyPartKT

@Component
class GenPackageImplKT : GenAnyPartKT<VBdObjectEntity> {

    @Autowired
    private lateinit var genCodeCommonFunction: GenCodeCommonFunctionKT

    override fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        return StringBuilder("package " + genCodeCommonFunction.getPackegeName(typeOfGenClass) + ";\n\n")
    }
}