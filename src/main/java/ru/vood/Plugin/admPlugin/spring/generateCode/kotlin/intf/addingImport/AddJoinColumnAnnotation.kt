package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

class AddJoinColumnAnnotation(override val paramOfAnnotation: ParamOfAnnotation) : AddAnyAnnotation {

    override val name: String
        get() = "JoinColumn"
    override val importString: String
        get() = "javax.persistence.JoinColumn"
}