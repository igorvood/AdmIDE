package ru.vood.Plugin.admPlugin.spring.intf

import com.jeeconf.hibernate.performancetuning.sqltracker.AssertSqlCount
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects
import ru.vood.Plugin.admPlugin.tune.PluginTunes

class VBdColomnsEntityServiceTest : BaseTest() {


    private lateinit var vBdColomnsEntityService: VBdColomnsEntityService

    private var vBdTableEntity = VBdTableEntity()

    private lateinit var vBdTableEntityService: VBdTableEntityService

    @Before
    fun bef() {
        vBdColomnsEntityService = ctx.getBean(VBdColomnsEntityService::class.java)
        pluginTunes = ctx.getBean(PluginTunes::class.java)
        vBdTableEntityService = ctx.getBean(VBdTableEntityService::class.java)
        val typeEntity = ObjectTypes.getTABLE()
        val vBdObjectEntityParent = RootObjects.getTABLE()

        val tabName = "TEST_CODE_TABLE_FOR_COLUMN"
        vBdTableEntity.setCode(tabName)
        vBdTableEntity.setName("TEST_NAME_TABLE")
        vBdTableEntity.setJavaClass(VBdTableEntity::class.java.toString())
        vBdTableEntity.setParent(vBdObjectEntityParent)
        vBdTableEntity.setTypeObject(typeEntity)

        vBdTableEntity = vBdTableEntityService.save(vBdTableEntity)
    }

    @After
    fun after() {
        vBdTableEntityService.delete(vBdTableEntity)
    }


    @Test
    fun save() {
        val colName = "Test_col_CODE".toUpperCase()
        var col = VBdColomnsEntity()
        col.code = colName
        col.name = "Name of $colName"
        col.setJavaClass(VBdTableEntity::class.java.toString())
        col.setParent(vBdTableEntity)
        col.setTypeObject(ObjectTypes.getCOLOMN())
        col.notNull = false
        col.typeColomn = ObjectTypes.getSTRING()
        col.typeValue = vBdTableEntityService.findByCode("STR_160")
        AssertSqlCount.reset()

        col = vBdColomnsEntityService.save(col)

        val newCol = "select * from all_tab_columns tabC where tabC.owner='${pluginTunes.owner}' and tabC.table_name='${pluginTunes.prefixTable}${col.parent.code}' and tabc.column_name = '${colName}'"
        val editCol = "select * from all_tab_columns tabC where tabC.owner='${pluginTunes.owner}' and tabC.table_name='${pluginTunes.prefixTable}${col.parent.code}' and tabc.column_name = '${colName}_1'"
        var query = em.createNativeQuery(newCol)
        val existsList = query.resultList

        col.notNull = true
        col.code = colName + "_1"

        col = vBdColomnsEntityService.save(col)

        query = em.createNativeQuery(editCol)
        val editList = query.resultList

        vBdColomnsEntityService.delete(col)

        query = em.createNativeQuery(editCol)
        val delList = query.resultList

        AssertSqlCount.assertInsertCount(2)
        AssertSqlCount.assertUpdateCount(2)
        AssertSqlCount.assertSelectCount(12)
        AssertSqlCount.assertDeleteCount(2)
        Assert.assertEquals(existsList.size, 1)
        Assert.assertEquals(editList.size, 1)
        Assert.assertEquals(delList.size, 0)
    }


    @Test
    fun findByParent() {
    }

    @Test
    fun findColomn() {
    }

}