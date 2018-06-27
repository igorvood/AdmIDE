package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import java.lang.reflect.Type
import javax.persistence.JoinColumn

class AddJoinColumnAnnotation(override val paramOfAnnotation: ParamOfAnnotation) : AddAnyAnnotation {

    override val name: Type
        get() = JoinColumn::class.java
    override val importString: String
        get() = "javax.persistence.JoinColumn"
}