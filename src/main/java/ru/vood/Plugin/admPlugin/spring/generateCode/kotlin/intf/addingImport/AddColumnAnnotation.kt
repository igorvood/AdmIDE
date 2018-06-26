package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

class AddColumnAnnotation(override val paramOfAnnotation: ParamOfAnnotation) : AddAnyAnnotation {

    override val name: String
        get() = "Column"
    override val importString: String
        get() = "javax.persistence.Column"
}