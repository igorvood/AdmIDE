package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import java.lang.reflect.Type
import javax.persistence.ManyToOne

class AddManyToOneAnnotation : AddAnyAnnotation {
    override val paramOfAnnotation: ParamOfAnnotation
        get() = mapOf("fetch" to "FetchType.LAZY") as ParamOfAnnotation
    override val name: Type
        get() = ManyToOne::class.java
    override val importString: String
        get() = "javax.persistence.FetchType - javax.persistence.ManyToOne"
}