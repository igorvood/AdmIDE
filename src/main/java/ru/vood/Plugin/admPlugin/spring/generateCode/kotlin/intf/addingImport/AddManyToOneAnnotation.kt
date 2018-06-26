package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

class AddManyToOneAnnotation : AddAnyAnnotation {
    override val paramOfAnnotation: ParamOfAnnotation
        get() = ParamOfAnnotation(mapOf("fetch" to "FetchType.LAZY"))
    override val name: String
        get() = "ManyToOne"
    override val importString: String
        get() = "javax.persistence.FetchType - javax.persistence.ManyToOne"
}