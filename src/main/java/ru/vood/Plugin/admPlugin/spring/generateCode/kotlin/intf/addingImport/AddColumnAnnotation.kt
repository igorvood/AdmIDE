package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import java.lang.reflect.Type
import javax.persistence.Column

class AddColumnAnnotation(override val paramOfAnnotation: ParamOfAnnotation) : AddAnyAnnotation {

    override val name: Type
        get() = Column::class.java
    override val importString: String
        get() = "javax.persistence.Column"
}