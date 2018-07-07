package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport.Message

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl.GenImportImplKT

class AddingClassPublisherTest : BaseTest() {

    private lateinit var addingClassPublisher: AddingClassPublisher

    private lateinit var genImportImplKT: GenImportImplKT

    @Before
    fun setUp() {
        addingClassPublisher = ctx.getBean(AddingClassPublisher::class.java)
        genImportImplKT = ctx.getBean(GenImportImplKT::class.java)
        genImportImplKT.clearImports()
    }

    @Test
    fun publish() {
        val s = "qwerty";
        addingClassPublisher.publish(this, s)
        println(genImportImplKT.genCode())
        Assert.assertEquals(genImportImplKT.genCode().toString(), "import $s;\n")
    }

}