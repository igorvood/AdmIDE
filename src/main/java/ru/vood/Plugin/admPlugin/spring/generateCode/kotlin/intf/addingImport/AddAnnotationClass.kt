package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport

import org.springframework.stereotype.Component
import java.lang.reflect.Type

@Component
class AddAnnotationClass : AddJavaClass() {

    override fun getCode(type: Type): String = getCode(type, ParamOfAnnotation())

    fun getCode(type: Type, paramOfAnnotation: ParamOfAnnotation): String {
        if (paramOfAnnotation.isEmpty()) return "@" + super.getCode(type)
        addingClassPublisher.publish(this, type)
        val par = paramOfAnnotation.asSequence()
                .map { pp -> pp.key + if (!pp.value?.isEmpty()) "=" + pp.value else "" }
                .reduce { s1, s2 -> s1 + ", " + s2 }
        return "@" + super.getCode(type) + "(" + par + ")"
    }

    // fun getTypeAndItImport(type: Type, paramOfAnnotation: ParamOfAnnotation): TypeAndItImport = TypeAndItImport(getCode(type, paramOfAnnotation), getFullNameClass(type))
}