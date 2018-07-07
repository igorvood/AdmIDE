package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenImportServiceKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport.Message.AddImportEvent

@Component
class GenImportImplKT : GenImportServiceKT, ApplicationListener<AddImportEvent> {

    private var listImports: HashSet<String> = HashSet()


    override fun genCode(): StringBuilder {
        var par = StringBuilder(listImports?.asSequence()
                .map { q -> getOneImport(q) }
                .reduce { s1, s2 -> s1 + s2 })
        return par
    }

    private fun getOneImport(fullNameClass: String) = "import $fullNameClass;\n"

    override fun onApplicationEvent(event: AddImportEvent) {
        if (listImports == null) listImports = HashSet()
        listImports.add(event.fullNameClass)
    }

    fun clearImports() {
        listImports.clear()
    }

}