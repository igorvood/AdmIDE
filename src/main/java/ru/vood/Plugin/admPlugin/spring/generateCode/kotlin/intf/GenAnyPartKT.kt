package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT

interface GenAnyPartKT<Q : VBdObjectEntity> {

    fun genCode(entity: Q, typeOfGenClass: TypeOfGenClassKT = TypeOfGenClassKT.ENTITY_CLASS): StringBuilder = genCode()

    fun genCode(): StringBuilder {
        return StringBuilder()
    }

}