package ru.vood.Plugin.admPlugin.spring.intf

import com.jeeconf.hibernate.performancetuning.sqltracker.AssertSqlCount
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects
import ru.vood.Plugin.admPlugin.tune.PluginTunes

class VBdIndexEntityServiceTest : BaseTest() {

    private lateinit var vBdIndexEntityService: VBdIndexEntityService

    private lateinit var vBdColomnsEntityService: VBdColomnsEntityService

    private var vBdTableEntity = VBdTableEntity()

    private lateinit var vBdTableEntityService: VBdTableEntityService

    private lateinit var pluginTunes: PluginTunes

    private lateinit var col: VBdColomnsEntity
    private val colNames = listOf<String>("Test_col_CODE_INDEX_1".toUpperCase(), "Test_col_CODE_INDEX_2".toUpperCase())

    @Before
    @Throws(Exception::class)
    fun setUp() {
        vBdIndexEntityService = ctx.getBean(VBdIndexEntityService::class.java)
        vBdColomnsEntityService = ctx.getBean(VBdColomnsEntityService::class.java)
        pluginTunes = ctx.getBean(PluginTunes::class.java)
        vBdTableEntityService = ctx.getBean(VBdTableEntityService::class.java)

        val typeEntity = ObjectTypes.getTABLE()
        val vBdObjectEntityParent = RootObjects.getTABLE()

        val tabName = "TEST_CODE_TABLE_FOR_INDEX"
        vBdTableEntity.setCode(tabName)
        vBdTableEntity.setName("${tabName} name ")
        vBdTableEntity.setJavaClass(VBdTableEntity::class.java.toString())
        vBdTableEntity.setParent(vBdObjectEntityParent)
        vBdTableEntity.setTypeObject(typeEntity)
        vBdTableEntity = vBdTableEntityService.save(vBdTableEntity)

        col = VBdColomnsEntity()
        col.code = colNames.get(0)
        col.name = "Name of ${colNames.get(0)}"
        col.setJavaClass(VBdTableEntity::class.java.toString())
        col.setParent(vBdTableEntity)
        col.setTypeObject(ObjectTypes.getCOLOMN())
        col.notNull = false
        col.typeColomn = ObjectTypes.getSTRING()
        col.typeValue = vBdTableEntityService.findByCode("STR_160")
        AssertSqlCount.reset()
        col = vBdColomnsEntityService.save(col)

        AssertSqlCount.reset()
    }

    @After
    fun after() {
        vBdTableEntityService.delete(vBdTableEntity)
    }


    @Test
    fun findByCode() {
        val indexName = "IDX_Test_col_CODE".toUpperCase()
        var index = VBdIndexEntity()
        index.code = indexName
        index.name = "Name of $indexName"
        index.setJavaClass(VBdIndexEntity::class.java.toString())
        index.setParent(vBdTableEntity)
        index.setTypeObject(ObjectTypes.getINDEX())
        index.addColomn(col)
        index.uniqueI = true
        AssertSqlCount.reset()

        index = vBdIndexEntityService.save(index)

        vBdIndexEntityService.delete(index)


        AssertSqlCount.assertInsertCount(2)
        AssertSqlCount.assertUpdateCount(0)
        AssertSqlCount.assertSelectCount(2)
        AssertSqlCount.assertDeleteCount(2)
//        Assert.assertEquals(existsList.size, 1)
//        Assert.assertEquals(editList.size, 1)
//        Assert.assertEquals(delList.size, 0)

//        index.notNull = false
//        index.typeColomn = ObjectTypes.getSTRING()
//        index.typeValue = vBdTableEntityService.findByCode("STR_160")


    }

    @Test
    fun save() {


    }

    @Test
    fun findByParent() {
    }

}