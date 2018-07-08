package ru.tora.generatedEntity.entity;

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "Z_CLIENT", schema = "VOOD")
@Inheritance(strategy = InheritanceType.JOINED)
open/*Наименование класса - Клиенты*/
class ZClientEntity {
    /*Уникальный Идентификатор*/
    @Id
    @GenericGenerator(name = "seqId", strategy = "ru.vood.Plugin.admPlugin.spring.entity.GeneratorId")
    @GeneratedValue(generator = "seqId")
    lateinit var id: BigDecimal

    /*Наименование поля - ФИО*/
    @Column(name = "NAME", nullable = true)
    lateinit var name: String

    /*Наименование поля - Дата рождения*/
    @Column(name = "DATE_BIRTH", nullable = true)
    @Temporal(TemporalType.DATE)
    lateinit var dateBirth: Date

    /*Наименование поля - Адреса*/
    @Column(name = "ADRESESS", nullable = true)
    lateinit var adresess: BigDecimal

}
