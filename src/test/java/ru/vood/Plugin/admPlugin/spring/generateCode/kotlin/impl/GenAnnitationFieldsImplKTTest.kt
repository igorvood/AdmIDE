package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest

class GenAnnitationFieldsImplKTTest : BaseTest() {

    private lateinit var genAnnitationFieldsImplKT: GenAnnitationFieldsImplKT

    @Before
    fun setUp() {
        genAnnitationFieldsImplKT = ctx.getBean(GenAnnitationFieldsImplKT::class.java)
    }

    @Test
    fun genCode() {
        genAnnitationFieldsImplKT.genCode()
    }

}