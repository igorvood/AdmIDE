package ru.vood.Plugin.admPlugin.spring.generateCode.Message

import org.springframework.context.ApplicationListener


class AddImportEventListener : ApplicationListener<AddImportEvent> {

    override fun onApplicationEvent(event: AddImportEvent) {

        val msgEvt = event
        //println("Received: " + msgEvt.getMessage())
    }
}