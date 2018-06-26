package ru.vood.Plugin.admPlugin.spring.generateCode.Message

import org.hibernate.annotations.GenericGenerator
import org.springframework.context.ApplicationEvent
import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "V_BD_OBJECT", schema = ParentForAll.SCHEMA, catalog = "")
@Inheritance(strategy = InheritanceType.JOINED)

class AddImportEvent : ApplicationEvent {

    val importMap = kotlin.collections.mapOf<String, String>(
            "Id" to "javax.persistence.*",
            "GenericGenerator" to "import org.hibernate.annotations.GenericGenerator",
            "GeneratedValue" to "javax.persistence.*",
            "Entity" to "org.springframework.data.annotation.Column"

    )

    @Id
    @GenericGenerator(name = "seqId", strategy = "ru.vood.Plugin.admPlugin.spring.entity.GeneratorId")
    @GeneratedValue(generator = "seqId")
    @Column(name = "ID", nullable = false, precision = 0)
    lateinit var id: BigDecimal
    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT", referencedColumnName = "ID")

    var fullNameClass: String

    constructor(source: Any, fullNameClass: String) : super(source) {
        this.fullNameClass = fullNameClass

    }
}