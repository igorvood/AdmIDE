package ru.vood.Plugin.admPlugin.spring.generateCode.testKotlin

import org.springframework.data.repository.CrudRepository
import java.math.BigDecimal

interface BdObjectRepository : CrudRepository<BDObject, BigDecimal>