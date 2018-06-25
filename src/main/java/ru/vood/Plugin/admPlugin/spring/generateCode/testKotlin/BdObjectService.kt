package ru.vood.Plugin.admPlugin.spring.generateCode.testKotlin

import java.math.BigDecimal

open interface BdObjectService {

    open fun save(entity: BDObject): BDObject

    open fun findOne(id: BigDecimal): BDObject

    open fun findByCodeAndParenCode(code: String, parentCode: String): BDObject
}