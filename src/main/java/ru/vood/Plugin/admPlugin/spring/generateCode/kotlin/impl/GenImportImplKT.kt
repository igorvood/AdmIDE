package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenImportServiceKT

@Component
class GenImportImplKT : GenImportServiceKT {

    override fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        return StringBuilder()
    }
}