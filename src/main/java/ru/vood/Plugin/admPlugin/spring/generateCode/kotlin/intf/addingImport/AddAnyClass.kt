package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import org.springframework.context.ApplicationListener
import ru.vood.Plugin.admPlugin.spring.generateCode.Message.AddImportEvent

interface AddAnyClass : ApplicationListener<AddImportEvent> {

    val name: String
    val importString: String

    override fun onApplicationEvent(event: AddImportEvent) {
        val msgEvt = event
    }
}