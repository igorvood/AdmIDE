package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import java.lang.reflect.Type
import javax.persistence.Id

class IdAnnotation : AddAnyAnnotation {
    override val paramOfAnnotation: ParamOfAnnotation
        get() = ParamOfAnnotation()
    override val name: Type
        get() = Id::class.java
    override val importString: String
        get() = "javax.persistence.Id"

}