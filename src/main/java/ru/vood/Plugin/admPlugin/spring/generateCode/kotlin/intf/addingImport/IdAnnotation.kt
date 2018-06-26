package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

class IdAnnotation : AddAnyAnnotation {
    override val paramOfAnnotation: ParamOfAnnotation
        get() = ParamOfAnnotation(mapOf())
    override val name: String
        get() = "Id"
    override val importString: String
        get() = "javax.persistence.Id"
}