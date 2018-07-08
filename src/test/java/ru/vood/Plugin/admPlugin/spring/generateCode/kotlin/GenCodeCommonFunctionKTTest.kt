package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.generateCode.impl.GenCodeCommonFunctionKT
import ru.vood.Plugin.admPlugin.generateCode.impl.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects
import ru.vood.Plugin.admPlugin.tune.PluginTunes

class GenCodeCommonFunctionKTTest : BaseTest() {

    private lateinit var genCodeCommonFunctionKT: GenCodeCommonFunctionKT

    private lateinit var vBdTableEntity: VBdTableEntity
    private lateinit var vBdTableEntityWithParent: VBdTableEntity

    private lateinit var pluginTunes: PluginTunes

    @Before
    fun setUp() {
        genCodeCommonFunctionKT = ctx.getBean(GenCodeCommonFunctionKT::class.java)
        pluginTunes = ctx.getBean(PluginTunes::class.java)

        val vBdObjectEntityParent = RootObjects.getTABLE()
        val typeEntity = ObjectTypes.getTABLE()
        vBdTableEntity = VBdTableEntity()
        val tabName = "TEST_CODE_TABLE"
        vBdTableEntity.setCode(tabName)
        vBdTableEntity.setName("$tabName Name")
        vBdTableEntity.setJavaClass(VBdTableEntity::class.java.toString())
        vBdTableEntity.setParent(vBdObjectEntityParent)
        vBdTableEntity.setTypeObject(typeEntity)

        vBdTableEntityWithParent = VBdTableEntity()
        val tabNamePar = "TEST_CODE_TABLE_par"
        vBdTableEntityWithParent.setCode(tabNamePar)
        vBdTableEntityWithParent.setName("$tabNamePar Name")
        vBdTableEntityWithParent.setJavaClass(VBdTableEntity::class.java.toString())
        vBdTableEntityWithParent.setParent(vBdTableEntity)
        vBdTableEntityWithParent.setTypeObject(typeEntity)

    }

    @Test
    fun getTableName() {
        val s = (pluginTunes.prefixTable + vBdTableEntity.code).toUpperCase()
        Assert.assertEquals(genCodeCommonFunctionKT.getTableName(vBdTableEntity).toString(), s)
    }

    @Test
    fun getClassName() {
        println(genCodeCommonFunctionKT.getClassName(vBdTableEntity))
        val sClass = "ZTestCodeTable"
        Assert.assertEquals(genCodeCommonFunctionKT.getClassName(vBdTableEntity).toString(), sClass + TypeOfGenClassKT.ENTITY_CLASS)
        Assert.assertEquals(genCodeCommonFunctionKT.getClassName(vBdTableEntity, TypeOfGenClassKT.ENTITY_CLASS).toString(), sClass + TypeOfGenClassKT.ENTITY_CLASS)
        Assert.assertEquals(genCodeCommonFunctionKT.getClassName(vBdTableEntity, TypeOfGenClassKT.IMPL_CLASS).toString(), sClass + TypeOfGenClassKT.IMPL_CLASS)
        Assert.assertEquals(genCodeCommonFunctionKT.getClassName(vBdTableEntity, TypeOfGenClassKT.SERVICE_CLASS).toString(), sClass + TypeOfGenClassKT.SERVICE_CLASS)
    }

    @Test
    fun getParametrName() {
        println(genCodeCommonFunctionKT.getParametrName(vBdTableEntity))
        val sClass = "zTestCodeTable"
        Assert.assertEquals(genCodeCommonFunctionKT.getParametrName(vBdTableEntity).toString(), sClass + TypeOfGenClassKT.ENTITY_CLASS + "Val")
        Assert.assertEquals(genCodeCommonFunctionKT.getParametrName(vBdTableEntity, TypeOfGenClassKT.ENTITY_CLASS).toString(), sClass + TypeOfGenClassKT.ENTITY_CLASS + "Val")
        Assert.assertEquals(genCodeCommonFunctionKT.getParametrName(vBdTableEntity, TypeOfGenClassKT.IMPL_CLASS).toString(), sClass + TypeOfGenClassKT.IMPL_CLASS + "Val")
        Assert.assertEquals(genCodeCommonFunctionKT.getParametrName(vBdTableEntity, TypeOfGenClassKT.SERVICE_CLASS).toString(), sClass + TypeOfGenClassKT.SERVICE_CLASS + "Val")
    }

    @Test
    fun getPackegeName() {
        println(genCodeCommonFunctionKT.getPackegeName(TypeOfGenClassKT.ENTITY_CLASS))
        val sClass = pluginTunes.packageIn + "."
        Assert.assertEquals(genCodeCommonFunctionKT.getPackegeName(TypeOfGenClassKT.ENTITY_CLASS).toString(), sClass + TypeOfGenClassKT.ENTITY_CLASS.toString().toLowerCase())
        Assert.assertEquals(genCodeCommonFunctionKT.getPackegeName(TypeOfGenClassKT.IMPL_CLASS).toString(), sClass + TypeOfGenClassKT.IMPL_CLASS.toString().toLowerCase())
        Assert.assertEquals(genCodeCommonFunctionKT.getPackegeName(TypeOfGenClassKT.SERVICE_CLASS).toString(), sClass + TypeOfGenClassKT.SERVICE_CLASS.toString().toLowerCase())
    }

    @Test
    fun getFullClassName() {
        println(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity, TypeOfGenClassKT.ENTITY_CLASS))
        val sPack = pluginTunes.packageIn + "."
        val sClass = "ZTestCodeTable"
        Assert.assertEquals(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity).toString(), sPack + TypeOfGenClassKT.ENTITY_CLASS.toString().toLowerCase() + "." + sClass + TypeOfGenClassKT.ENTITY_CLASS);
        Assert.assertEquals(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity, TypeOfGenClassKT.ENTITY_CLASS).toString(), sPack + TypeOfGenClassKT.ENTITY_CLASS.toString().toLowerCase() + "." + sClass + TypeOfGenClassKT.ENTITY_CLASS);
        Assert.assertEquals(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity, TypeOfGenClassKT.IMPL_CLASS).toString(), (sPack + TypeOfGenClassKT.IMPL_CLASS.toString().toLowerCase() + "." + sClass) + TypeOfGenClassKT.IMPL_CLASS);
        Assert.assertEquals(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity, TypeOfGenClassKT.SERVICE_CLASS).toString(), (sPack + TypeOfGenClassKT.SERVICE_CLASS.toString().toLowerCase() + "." + sClass) + TypeOfGenClassKT.SERVICE_CLASS);
    }

    @Test
    fun getExtendsClassName() {
        println(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity, TypeOfGenClassKT.ENTITY_CLASS))

        Assert.assertEquals(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity).toString(), "")
        Assert.assertEquals(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity, TypeOfGenClassKT.ENTITY_CLASS).toString(), "")
        Assert.assertEquals(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity, TypeOfGenClassKT.IMPL_CLASS).toString(), "")
        Assert.assertEquals(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity, TypeOfGenClassKT.SERVICE_CLASS).toString(), "")

        println(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent))
        val s = " extends ru.tora.generatedEntity.entity.ZTestCodeTableEntity"
        Assert.assertEquals(s, genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent).toString())
        Assert.assertEquals(s, genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent, TypeOfGenClassKT.ENTITY_CLASS).toString())
        Assert.assertEquals("", genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent, TypeOfGenClassKT.IMPL_CLASS).toString())
        Assert.assertEquals("", genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent, TypeOfGenClassKT.SERVICE_CLASS).toString())
    }


    @Test
    fun getIdField() {
    }

    @Test
    fun isRootEntity() {
    }

    @Test
    fun toCamelCase() {
    }

}