package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import java.lang.reflect.Type

interface AddJavaClassService {
    fun getFullNameClass(type: Type): String
    fun getCode(type: Type): String
    fun getTypeAndItImport(type: Type): TypeAndItImport = TypeAndItImport(getCode(type), getFullNameClass(type))
}