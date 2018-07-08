package ru.tora.generatedEntity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Z_CLIENT_ORG", schema = "VOOD")
/*Наименование класса - Юридические лица*/
class ZClientOrgEntity : ru.tora.generatedEntity.entity.ZClientEntity() {
    /*Наименование поля - КПП*/
    @Column(name = "KPP", nullable = true)
    lateinit var kpp: String

}