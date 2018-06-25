package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT

interface GenAnyPartKT {

    fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT = TypeOfGenClassKT.ENTITY_CLASS): StringBuilder
}