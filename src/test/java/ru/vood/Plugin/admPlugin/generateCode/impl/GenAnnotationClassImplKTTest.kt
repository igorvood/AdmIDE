package ru.vood.Plugin.admPlugin.generateCode.impl

import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects

class GenAnnotationClassImplKTTest : BaseTest() {
    private lateinit var genAnnotationClassImplKT: GenAnnotationClassImplKT

    private lateinit var genImportImplKT: GenImportImplKT

    private lateinit var vBdTableEntity: VBdTableEntity

    private lateinit var vBdTableEntityWithParent: VBdTableEntity

    @Before
    fun setUp() {
        genAnnotationClassImplKT = ctx.getBean(GenAnnotationClassImplKT::class.java)
        genImportImplKT = ctx.getBean(GenImportImplKT::class.java)

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
        val tabNameWithParent = "TEST_CODE_TABLE_WITH_PARENT"
        vBdTableEntityWithParent.setCode(tabNameWithParent)
        vBdTableEntityWithParent.setName("$tabNameWithParent Name")
        vBdTableEntityWithParent.setJavaClass(VBdTableEntity::class.java.toString())
        vBdTableEntityWithParent.setParent(vBdTableEntity)
        vBdTableEntityWithParent.setTypeObject(typeEntity)

    }

    @Test
    fun genCodeNoParent() {
        println(genAnnotationClassImplKT.genCode(vBdTableEntity))

        println(genImportImplKT.genCode())
    }

    @Test
    fun genCodeWithParent() {
        println(genAnnotationClassImplKT.genCode(vBdTableEntityWithParent))

        println(genImportImplKT.genCode())
    }
}
