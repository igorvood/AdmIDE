package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

interface AddAnyAnnotation : AddAnyClass {
    val paramOfAnnotation: ParamOfAnnotation

    fun getImport() = "import $importString;"

    fun getAnnotation(): String {
        var s: String = ""
        if (!paramOfAnnotation.pairParam.isEmpty()) {
            paramOfAnnotation.pairParam.forEach()
        }
        return "@$name"
    }
}