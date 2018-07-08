package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.generateCode.impl.GenCodeCommonFunctionKT
import ru.vood.Plugin.admPlugin.generateCode.impl.TypeOfGenClass
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
        Assert.assertEquals(genCodeCommonFunctionKT.getClassName(vBdTableEntity).toString(), sClass + TypeOfGenClass.ENTITY_CLASS)
        Assert.assertEquals(genCodeCommonFunctionKT.getClassName(vBdTableEntity, TypeOfGenClass.ENTITY_CLASS).toString(), sClass + TypeOfGenClass.ENTITY_CLASS)
        Assert.assertEquals(genCodeCommonFunctionKT.getClassName(vBdTableEntity, TypeOfGenClass.IMPL_CLASS).toString(), sClass + TypeOfGenClass.IMPL_CLASS)
        Assert.assertEquals(genCodeCommonFunctionKT.getClassName(vBdTableEntity, TypeOfGenClass.SERVICE_CLASS).toString(), sClass + TypeOfGenClass.SERVICE_CLASS)
    }

    @Test
    fun getParametrName() {
        println(genCodeCommonFunctionKT.getParametrName(vBdTableEntity))
        val sClass = "zTestCodeTable"
        Assert.assertEquals(genCodeCommonFunctionKT.getParametrName(vBdTableEntity).toString(), sClass + TypeOfGenClass.ENTITY_CLASS + "Val")
        Assert.assertEquals(genCodeCommonFunctionKT.getParametrName(vBdTableEntity, TypeOfGenClass.ENTITY_CLASS).toString(), sClass + TypeOfGenClass.ENTITY_CLASS + "Val")
        Assert.assertEquals(genCodeCommonFunctionKT.getParametrName(vBdTableEntity, TypeOfGenClass.IMPL_CLASS).toString(), sClass + TypeOfGenClass.IMPL_CLASS + "Val")
        Assert.assertEquals(genCodeCommonFunctionKT.getParametrName(vBdTableEntity, TypeOfGenClass.SERVICE_CLASS).toString(), sClass + TypeOfGenClass.SERVICE_CLASS + "Val")
    }

    @Test
    fun getPackegeName() {
        println(genCodeCommonFunctionKT.getPackegeName(TypeOfGenClass.ENTITY_CLASS))
        val sClass = pluginTunes.packageIn + "."
        Assert.assertEquals(genCodeCommonFunctionKT.getPackegeName(TypeOfGenClass.ENTITY_CLASS).toString(), sClass + TypeOfGenClass.ENTITY_CLASS.toString().toLowerCase())
        Assert.assertEquals(genCodeCommonFunctionKT.getPackegeName(TypeOfGenClass.IMPL_CLASS).toString(), sClass + TypeOfGenClass.IMPL_CLASS.toString().toLowerCase())
        Assert.assertEquals(genCodeCommonFunctionKT.getPackegeName(TypeOfGenClass.SERVICE_CLASS).toString(), sClass + TypeOfGenClass.SERVICE_CLASS.toString().toLowerCase())
    }

    @Test
    fun getFullClassName() {
        println(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity, TypeOfGenClass.ENTITY_CLASS))
        val sPack = pluginTunes.packageIn + "."
        val sClass = "ZTestCodeTable"
        Assert.assertEquals(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity).toString(), sPack + TypeOfGenClass.ENTITY_CLASS.toString().toLowerCase() + "." + sClass + TypeOfGenClass.ENTITY_CLASS);
        Assert.assertEquals(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity, TypeOfGenClass.ENTITY_CLASS).toString(), sPack + TypeOfGenClass.ENTITY_CLASS.toString().toLowerCase() + "." + sClass + TypeOfGenClass.ENTITY_CLASS);
        Assert.assertEquals(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity, TypeOfGenClass.IMPL_CLASS).toString(), (sPack + TypeOfGenClass.IMPL_CLASS.toString().toLowerCase() + "." + sClass) + TypeOfGenClass.IMPL_CLASS);
        Assert.assertEquals(genCodeCommonFunctionKT.getFullClassName(vBdTableEntity, TypeOfGenClass.SERVICE_CLASS).toString(), (sPack + TypeOfGenClass.SERVICE_CLASS.toString().toLowerCase() + "." + sClass) + TypeOfGenClass.SERVICE_CLASS);
    }

    @Test
    fun getExtendsClassName() {
        println(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity, TypeOfGenClass.ENTITY_CLASS))

        Assert.assertEquals(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity).toString(), "")
        Assert.assertEquals(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity, TypeOfGenClass.ENTITY_CLASS).toString(), "")
        Assert.assertEquals(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity, TypeOfGenClass.IMPL_CLASS).toString(), "")
        Assert.assertEquals(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntity, TypeOfGenClass.SERVICE_CLASS).toString(), "")

        println(genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent))
        val s = " extends ru.tora.generatedEntity.entity.ZTestCodeTableEntity"
        Assert.assertEquals(s, genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent).toString())
        Assert.assertEquals(s, genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent, TypeOfGenClass.ENTITY_CLASS).toString())
        Assert.assertEquals("", genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent, TypeOfGenClass.IMPL_CLASS).toString())
        Assert.assertEquals("", genCodeCommonFunctionKT.getExtendsClassName(vBdTableEntityWithParent, TypeOfGenClass.SERVICE_CLASS).toString())
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