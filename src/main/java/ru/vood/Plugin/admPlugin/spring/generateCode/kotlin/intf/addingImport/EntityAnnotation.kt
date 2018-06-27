package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import java.lang.reflect.Type
import javax.persistence.Entity

class EntityAnnotation : AddAnyAnnotation {
    override val paramOfAnnotation: ParamOfAnnotation
        get() = ParamOfAnnotation()
    override val name: Type
        get() = Entity::class.java
    override val importString: String
        get() = "javax.persistence.Entity"
}