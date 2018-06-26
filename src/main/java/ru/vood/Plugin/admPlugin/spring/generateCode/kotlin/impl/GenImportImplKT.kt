package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.generateCode.Message.AddImportEvent
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenImportServiceKT

@Component
class GenImportImplKT : GenImportServiceKT, ApplicationListener<AddImportEvent> {

    override fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        return StringBuilder()
    }

    override fun onApplicationEvent(event: AddImportEvent) {

        val msgEvt = event
        //println("Received: " + msgEvt.getMessage())
    }
}