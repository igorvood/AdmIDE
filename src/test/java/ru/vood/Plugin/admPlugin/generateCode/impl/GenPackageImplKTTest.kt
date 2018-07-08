package ru.vood.Plugin.admPlugin.generateCode.impl

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity

class GenPackageImplKTTest : BaseTest() {

    private lateinit var genPackageImplKT: GenPackageImplKT

    @Before
    fun setUp() {
        genPackageImplKT = ctx.getBean(GenPackageImplKT::class.java)
        //genImportImplKT = ctx.getBean(GenImportImplKT::class.java)
    }

    @Test
    fun genCodeENTITY_CLASS() {
        Assert.assertSame("package ru.tora.generatedEntity.entity;\n\n", genPackageImplKT.genCode(VBdObjectEntity()).toString())
    }

    @Test
    fun genCodeENTITY_CLASS1() {
        Assert.assertSame("package ru.tora.generatedEntity.entity;\n", genPackageImplKT.genCode(VBdObjectEntity(), TypeOfGenClassKT.ENTITY_CLASS).toString())
    }

    @Test
    fun genCodeSERVICE_CLASS() {
        Assert.assertSame("package ru.tora.generatedEntity.entity;\n", genPackageImplKT.genCode(VBdObjectEntity(), TypeOfGenClassKT.SERVICE_CLASS).toString())
    }

    @Test
    fun genCodeIMPL_CLASS() {
        Assert.assertSame("package ru.tora.generatedEntity.entity;\n", genPackageImplKT.genCode(VBdObjectEntity(), TypeOfGenClassKT.IMPL_CLASS).toString())
    }

}