package ru.vood.Plugin.admPlugin.spring.generateCode.testKotlin

import org.springframework.context.ApplicationListener
import ru.vood.Plugin.admPlugin.spring.generateCode.Message.AddImportEvent

class Main(val s: String) {}

interface AddAnyClass : ApplicationListener<AddImportEvent> {
    override fun onApplicationEvent(event: AddImportEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class ParamAnnotation(val pairParam: Map<String, String>)

class AddAnotation(/*val nameAnnotation : String, */val importString: String, val paramAnnotation: ParamAnnotation?)

class AnnotationMap {
    val importMap = kotlin.collections.mapOf<String, AddAnotation>(
            "Id" to AddAnotation("javax.persistence.*", null),
            "1Id" to AddAnotation("javax.persistence.*", null)
//            @GeneratedValue(generator = "seqId")
//    @Column(name = "ID", nullable = false, precision = 0)
//    lateinit var id: BigDecimal
//    @Basic
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PARENT", referencedColumnName = "ID")

    )
}


fun main(args: Array<String>) {


    println(Main("").javaClass)
}

