package ru.vood.Plugin.admPlugin.generateCode.impl.intf

import ru.vood.Plugin.admPlugin.generateCode.impl.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity

interface GenAnyPartKT<Q : VBdObjectEntity> {

    fun genCode(entity: Q, typeOfGenClass: TypeOfGenClassKT = TypeOfGenClassKT.ENTITY_CLASS): StringBuilder = genCode()

    fun genCode(): StringBuilder {
        return StringBuilder()
    }

}