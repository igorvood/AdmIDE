package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

interface AddAnyAnnotation : AddAnyClass {
    val paramOfAnnotation: ParamOfAnnotation

    fun getImport() = "import $importString;"

    fun getAnnotation(): String {
        var s: String = paramOfAnnotation?.asSequence()
                .map { pa -> pa.key + if (pa.value?.isEmpty()) "" else "= " + pa.value }
                .reduce { total, next -> total + " , " + next }
        s = if (s.isEmpty()) "" else "($s)"
        return "@$name$s"
    }
}