package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin

enum class TypeOfGenClassKT(val nameClass: String/*, val pack: String*/) {
    ENTITY_CLASS("Entity"),
    IMPL_CLASS("Impl"),
    SERVICE_CLASS("Service");

    override fun toString(): String {
        return "$nameClass"
    }

}

