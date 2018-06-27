package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import org.springframework.context.ApplicationListener
import ru.vood.Plugin.admPlugin.spring.generateCode.Message.AddImportEvent
import java.lang.reflect.Type

interface AddAnyClass : ApplicationListener<AddImportEvent> {
    val name: Type
    val importString: String

//    var addedImport: HashMap<Type, String>
//
//    override fun onApplicationEvent(event: AddImportEvent) {
//        if (addedImport == null) addedImport = HashMap<Type, String>()
//        val msgEvt = event
//    }
}