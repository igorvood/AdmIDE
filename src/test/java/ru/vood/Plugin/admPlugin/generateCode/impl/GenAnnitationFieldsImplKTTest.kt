package ru.vood.Plugin.admPlugin.generateCode.impl

import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects
import ru.vood.Plugin.admPlugin.spring.referenceBook.Tables

class GenAnnitationFieldsImplKTTest : BaseTest() {

    private lateinit var genAnnitationFieldsImplKT: GenAnnitationFieldsImplKT

    private lateinit var genImportImplKT: GenImportImplKT

    private lateinit var vBdTableEntity: VBdTableEntity

    private lateinit var vBdColomnsEntity: VBdColomnsEntity

    @Before
    fun setUp() {
        genAnnitationFieldsImplKT = ctx.getBean(GenAnnitationFieldsImplKT::class.java)
        genImportImplKT = ctx.getBean(GenImportImplKT::class.java)

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
    fun genCode() {
        vBdColomnsEntity.typeColomn = ObjectTypes.getSTRING()
        vBdColomnsEntity.typeValue = Tables.getAny("STR_160")
        println(genAnnitationFieldsImplKT.genCode(vBdColomnsEntity))

        vBdColomnsEntity.typeColomn = ObjectTypes.getNUMBER()
        vBdColomnsEntity.typeValue = Tables.getAny("NUM_17_2")
        println(genAnnitationFieldsImplKT.genCode(vBdColomnsEntity))

        vBdColomnsEntity.typeColomn = ObjectTypes.getBOOLEAN()
        vBdColomnsEntity.typeValue = Tables.getAny("BOOLEAN")
        println(genAnnitationFieldsImplKT.genCode(vBdColomnsEntity))

        vBdColomnsEntity.typeColomn = ObjectTypes.getDATE()
        vBdColomnsEntity.typeValue = Tables.getAny("DATE")
        println(genAnnitationFieldsImplKT.genCode(vBdColomnsEntity))

        vBdColomnsEntity.typeColomn = ObjectTypes.getARRAY()
        vBdColomnsEntity.typeValue = Tables.getAny("address_ARR")
        println(genAnnitationFieldsImplKT.genCode(vBdColomnsEntity))

        println(genImportImplKT.genCode())
    }

}