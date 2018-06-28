package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import ru.vood.Plugin.admPlugin.spring.generateCode.Message.AddImportEvent

interface CreateApplicationEvent {
    fun sendMessage(msg: AddImportEvent) {
        msg.fullNameClass
        println("CreateApplicationEvent.sendMessage    -> TODO(not implemented) ${msg.fullNameClass}")
    }
}