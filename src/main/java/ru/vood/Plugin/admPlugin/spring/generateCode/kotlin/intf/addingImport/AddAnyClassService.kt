package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity

interface AddAnyClassService {
    fun getFullNameClass(entity: VBdObjectEntity): String
    fun getCode(entity: VBdObjectEntity): String
    fun getTypeAndItImport(entity: VBdObjectEntity): TypeAndItImport = TypeAndItImport(getCode(entity), getFullNameClass(entity))
}
