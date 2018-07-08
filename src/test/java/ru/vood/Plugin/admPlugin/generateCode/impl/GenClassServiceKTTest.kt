package ru.vood.Plugin.admPlugin.generateCode.impl

import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects

class GenClassServiceKTTest : BaseTest() {

    private lateinit var genClassServiceKT: GenClassServiceKT

    private lateinit var genAnnotationClassImplKT: GenAnnotationClassImplKT

    private lateinit var vBdTableEntityService: VBdTableEntityService

    private lateinit var genImportImplKT: GenImportImplKT

    private lateinit var vBdTableEntity: VBdTableEntity

    private lateinit var vBdTableEntityWithParent: VBdTableEntity

    private lateinit var vBdColomnsEntity2: VBdColomnsEntity

    private lateinit var vBdColomnsEntity1: VBdColomnsEntity


    @Before
    fun setUp() {
        genAnnotationClassImplKT = ctx.getBean(GenAnnotationClassImplKT::class.java)
        genImportImplKT = ctx.getBean(GenImportImplKT::class.java)
        vBdTableEntityService = ctx.getBean(VBdTableEntityService::class.java)

        genClassServiceKT = ctx.getBean(GenClassServiceKT::class.java)

        val vBdObjectEntityParent = RootObjects.getTABLE()
        val typeEntity = ObjectTypes.getTABLE()


//        val tabName = "TEST_CODE_TABLE"
//        vBdTableEntity.setCode(tabName)
//        vBdTableEntity.setName("$tabName Name")
//        vBdTableEntity.setJavaClass(VBdTableEntity::class.java.toString())
//        vBdTableEntity.setParent(vBdObjectEntityParent)
//        vBdTableEntity.setTypeObject(typeEntity)
//
//
//        vBdTableEntityWithParent = VBdTableEntity()
//        val tabNameWithParent = "TEST_CODE_TABLE_WITH_PARENT"
//        vBdTableEntityWithParent.setCode(tabNameWithParent)
//        vBdTableEntityWithParent.setName("$tabNameWithParent Name")
//        vBdTableEntityWithParent.setJavaClass(VBdTableEntity::class.java.toString())
//        vBdTableEntityWithParent.setParent(vBdTableEntity)
//        vBdTableEntityWithParent.setTypeObject(typeEntity)
//
//        val s = "column1"
//        vBdColomnsEntity1 = VBdColomnsEntity()
//        vBdColomnsEntity1.code = s
//        vBdColomnsEntity1.name = s + " Name"
//        vBdColomnsEntity1.typeColomn = ObjectTypes.getSTRING()
//        vBdColomnsEntity1.setJavaClass(VBdColomnsEntity::class.java.toString())
//        vBdColomnsEntity1.setParent(vBdTableEntity)
//        vBdColomnsEntity1.setTypeObject(ObjectTypes.getCOLOMN())
//        vBdColomnsEntity1.notNull = false
//        vBdColomnsEntity1.typeValue = vBdTableEntityService.findByCode("STR_160")
//
//
//        val s2 = "column2"
//        vBdColomnsEntity2 = VBdColomnsEntity()
//        vBdColomnsEntity2.code = s2
//        vBdColomnsEntity2.name = s2 + " Name"
//        vBdColomnsEntity2.typeColomn = ObjectTypes.getSTRING()
//        vBdColomnsEntity2.setJavaClass(VBdColomnsEntity::class.java.toString())
//        vBdColomnsEntity2.setParent(vBdTableEntityWithParent)
//        vBdColomnsEntity2.setTypeObject(ObjectTypes.getCOLOMN())
//        vBdColomnsEntity2.notNull = true
//        vBdColomnsEntity2.typeValue = vBdTableEntityService.findByCode("STR_160")

    }

    @Test
    fun genCode1() {
        val vBdTableEntity1 = vBdTableEntityService.findByCode("CLIENT")
        println("=====================================================")
        println(genClassServiceKT.genCode(vBdTableEntity1))
    }

    @Test
    fun genCode2() {
        val vBdTableEntity1 = vBdTableEntityService.findByCode("CLIENT_ORG")
        println("=====================================================")
        println(genClassServiceKT.genCode(vBdTableEntity1))

    }

}