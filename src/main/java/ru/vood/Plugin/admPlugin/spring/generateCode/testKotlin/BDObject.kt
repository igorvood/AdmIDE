package ru.vood.Plugin.admPlugin.spring.generateCode.testKotlin

import org.hibernate.annotations.GenericGenerator
import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll.SCHEMA
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "V_BD_OBJECT", schema = SCHEMA, catalog = "")
@Inheritance(strategy = InheritanceType.JOINED)
open class BDObject {
    @Id
    @GenericGenerator(name = "seqId", strategy = "ru.vood.Plugin.admPlugin.spring.entity.GeneratorId")
    @GeneratedValue(generator = "seqId")
    @Column(name = "ID", nullable = false, precision = 0)
    lateinit var id: BigDecimal

    @Basic
    @Column(name = "CODE", nullable = false, length = 50)
    lateinit var code: String

    @Basic
    @Column(name = "NAME", nullable = false, length = 250)
    lateinit var name: String

    @Basic
    @Column(name = "JAVA_CLASS", nullable = false, length = 512)
    lateinit var javaClass: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT", referencedColumnName = "ID")
    lateinit var parent: BDObject

}