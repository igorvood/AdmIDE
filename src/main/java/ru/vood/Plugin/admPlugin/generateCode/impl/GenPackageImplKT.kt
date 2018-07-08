package ru.vood.Plugin.admPlugin.generateCode.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenAnyPartKT
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity

@Component
class GenPackageImplKT : GenAnyPartKT<VBdObjectEntity> {

    @Autowired
    private lateinit var genCodeCommonFunction: GenCodeCommonFunctionKT

    override fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        return StringBuilder("package " + genCodeCommonFunction.getPackegeName(typeOfGenClass) + ";\n\n")
    }
}