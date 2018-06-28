package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import ru.vood.Plugin.admPlugin.spring.generateCode.Message.AddImportEvent
import java.lang.reflect.Type

open class AddJavaClass : AddJavaClassService, CreateApplicationEvent {
    override fun getFullNameClass(type: Type): String = type.toString().substringAfterLast(" ")

    override fun getCode(type: Type): String {
        sendMessage(AddImportEvent(this, getFullNameClass(type)))
        return type.toString().substringAfterLast(".")
    }
}