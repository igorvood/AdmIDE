package ru.vood.Plugin.admPlugin.generateCode.impl.intf.addingImport

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.generateCode.impl.GenImportImplKT
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity

class AddJavaClassTest : BaseTest() {

    private lateinit var genImportImplKT: GenImportImplKT
    private lateinit var addJavaClass: AddJavaClass


    @Before
    fun setUp() {
        genImportImplKT = ctx.getBean(GenImportImplKT::class.java)
        addJavaClass = ctx.getBean("addJavaClass", AddJavaClass::class.java)
        genImportImplKT.clearImports()
    }


    @Test
    fun genCode() {
        genImportImplKT.clearImports()
        Assert.assertEquals(addJavaClass.getCode(VBdObjectTypeEntity::class.java), "VBdObjectTypeEntity")
        Assert.assertEquals(addJavaClass.getCode(VBdObjectEntity::class.java), "VBdObjectEntity")
        Assert.assertTrue(genImportImplKT.genCode().length > 0)
        println(genImportImplKT.genCode())
    }

    @Test
    fun getFullNameClass() {
        genImportImplKT.clearImports()
        Assert.assertEquals(addJavaClass.getFullNameClass(VBdObjectTypeEntity::class.java), "ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity")
        //Assert.assertTrue(genImportImplKT.genCode().length>0)
    }


}