package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects
import ru.vood.Plugin.admPlugin.spring.referenceBook.Tables

class GenFieldsImplKTTest : BaseTest() {

    private lateinit var genFieldsImplKT: GenFieldsImplKT

    private lateinit var vBdTableEntity: VBdTableEntity

    private lateinit var vBdColomnsEntity: VBdColomnsEntity

    @Before
    fun setUp() {
        genFieldsImplKT = ctx.getBean(GenFieldsImplKT::class.java)

        //pluginTunes = ctx.getBean(PluginTunes::class.java)

        val vBdObjectEntityParent = RootObjects.getTABLE()
        val typeEntity = ObjectTypes.getTABLE()
        vBdTableEntity = VBdTableEntity()
        val tabName = "TEST_CODE_TABLE"
        val colName = "TEST_CODE_COLUMN"
        vBdTableEntity.setCode(tabName)
        vBdTableEntity.setName("$tabName Name")
        vBdTableEntity.setJavaClass(VBdTableEntity::class.java.toString())
        vBdTableEntity.setParent(vBdObjectEntityParent)
        vBdTableEntity.setTypeObject(typeEntity)
        vBdTableEntity.toType = vBdTableEntity

        vBdColomnsEntity = VBdColomnsEntity()
        vBdColomnsEntity.notNull = true
        vBdColomnsEntity.code = colName
        vBdColomnsEntity.name = colName + " Name"
        vBdColomnsEntity.typeValue = vBdTableEntity
    }

    @Test
    fun genCodeSTRING() {
        vBdColomnsEntity.typeColomn = ObjectTypes.getSTRING()
        vBdColomnsEntity.typeValue = Tables.getAny("STR_160")
        println(genFieldsImplKT.genCode(vBdColomnsEntity))
        Assert.assertTrue(genFieldsImplKT.genCode(vBdColomnsEntity).contains("val testCodeColumn :  String \n"))

        //Assert.assertEquals(, genFieldsImplKT.genCode(vBdColomnsEntity).toString())
    }

    @Test
    fun genCodeNUMBER() {

        vBdColomnsEntity.typeColomn = ObjectTypes.getNUMBER()
        vBdColomnsEntity.typeValue = Tables.getAny("NUM_17_2")
        //Assert.assertEquals("val testCodeColumn :  BigDecimal \n", genFieldsImplKT.genCode(vBdColomnsEntity).toString())
        println(genFieldsImplKT.genCode(vBdColomnsEntity))
        Assert.assertTrue(genFieldsImplKT.genCode(vBdColomnsEntity).contains("val testCodeColumn :  BigDecimal \n"))
    }

    @Test
    fun genCodeBOOLEAN() {
        vBdColomnsEntity.typeColomn = ObjectTypes.getBOOLEAN()
        vBdColomnsEntity.typeValue = Tables.getAny("BOOLEAN")
        //Assert.assertEquals("val testCodeColumn :  Boolean \n", genFieldsImplKT.genCode(vBdColomnsEntity).toString())
        println(genFieldsImplKT.genCode(vBdColomnsEntity))
        Assert.assertTrue(genFieldsImplKT.genCode(vBdColomnsEntity).contains("val testCodeColumn :  Boolean \n"))
    }

    @Test
    fun genCodeDATE() {

        vBdColomnsEntity.typeColomn = ObjectTypes.getDATE()
        vBdColomnsEntity.typeValue = Tables.getAny("DATE")
        //Assert.assertEquals("val testCodeColumn :  Date \n", genFieldsImplKT.genCode(vBdColomnsEntity).toString())
        println(genFieldsImplKT.genCode(vBdColomnsEntity))
        Assert.assertTrue(genFieldsImplKT.genCode(vBdColomnsEntity).contains("val testCodeColumn :  Date \n"))
    }

    @Test
    fun genCodeARRAY() {
        vBdColomnsEntity.typeColomn = ObjectTypes.getARRAY()
        vBdColomnsEntity.typeValue = Tables.getAny("address_ARR")
        //Assert.assertEquals("val testCodeColumn :  BigDecimal \n", genFieldsImplKT.genCode(vBdColomnsEntity).toString())
        println(genFieldsImplKT.genCode(vBdColomnsEntity))
        Assert.assertTrue(genFieldsImplKT.genCode(vBdColomnsEntity).contains("val testCodeColumn :  BigDecimal \n"))
    }

    @Test
    fun genCodeREFERENCE() {
        vBdColomnsEntity.typeColomn = ObjectTypes.getREFERENCE()
        vBdColomnsEntity.typeValue = vBdTableEntity
        //Assert.assertEquals("val testCodeColumn :  ZTestCodeTableEntity \n", genFieldsImplKT.genCode(vBdColomnsEntity).toString())
        println(genFieldsImplKT.genCode(vBdColomnsEntity))
        Assert.assertTrue(genFieldsImplKT.genCode(vBdColomnsEntity).contains("val testCodeColumn :  ZTestCodeTableEntity \n"))
    }

}