package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

class AddBasicAnnotation : AddAnyAnnotation {
    override val paramOfAnnotation: ParamOfAnnotation
        get() = ParamOfAnnotation(mapOf())
    override val name: String
        get() = "Basic"
    override val importString: String
        get() = "javax.persistence.Basic"
}