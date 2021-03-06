package ru.vood.Plugin.admPlugin.spring.intf;

import com.jeeconf.hibernate.performancetuning.sqltracker.AssertSqlCount;
import com.jeeconf.hibernate.performancetuning.sqltracker.QueryCountInfoHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.vood.Plugin.admPlugin.BaseTest;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.except.CoreExeption;
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects;

import javax.persistence.Query;
import java.util.List;


public class VBdTableEntityServiceTest extends BaseTest {

//    private VBdTableEntityService vBdTableEntityService;
//
//    private VBdObjectTypeEntityService vBdObjectTypeEntityService;
//
//    private PluginTunes pluginTunes;

    @Before
    public void setVar() {
//        vBdTableEntityService = ctx.getBean(VBdTableEntityService.class);
//        vBdObjectTypeEntityService = ctx.getBean(VBdObjectTypeEntityService.class);
//        pluginTunes = ctx.getBean(PluginTunes.class);
    }

    @Test
    public void editTest() {
        AssertSqlCount.reset();
        VBdTableEntity bdTableEntity = tableEntityService.save(tableEntity);

        String checkTable = "select allt.table_name from all_all_tables allt where allt.owner='" + pluginTunes.getOwner() + "' and allt.table_name='" + pluginTunes.getPrefixTable() + getTabName() + "'";
        String checkTableRenamed = "select allt.table_name from all_all_tables allt where allt.owner='" + pluginTunes.getOwner() + "' and allt.table_name='" + pluginTunes.getPrefixTable() + getTabName() + "1'";
        Query query = em.createNativeQuery(checkTable);
        List existsList = query.getResultList();

        bdTableEntity.setCode(getTabName() + "1");

        bdTableEntity = tableEntityService.save(bdTableEntity);

        List notExistsListRenamed = em.createNativeQuery(checkTableRenamed).getResultList();
        tableEntityService.delete(bdTableEntity);

        List notExistsList = em.createNativeQuery(checkTable).getResultList();

        AssertSqlCount.assertInsertCount(2);
        AssertSqlCount.assertUpdateCount(1);
        AssertSqlCount.assertSelectCount(7);
        Assert.assertNotNull(bdTableEntity.getId());
        AssertSqlCount.assertDeleteCount(2);

        Assert.assertEquals(existsList.size(), 1);
        Assert.assertEquals(notExistsListRenamed.size(), 1);
        Assert.assertEquals(notExistsList.size(), 0);

    }

    @Test
    public void saveTest() {
        AssertSqlCount.reset();

        VBdTableEntity bdTableEntity = tableEntityService.save(tableEntity);

        String checkTable = "select allt.table_name from all_all_tables allt where allt.owner='" + pluginTunes.getOwner() + "' and allt.table_name='" + pluginTunes.getPrefixTable() + getTabName() + "'";
        Query query = em.createNativeQuery(checkTable);
        List existsList = query.getResultList();

        tableEntityService.delete(bdTableEntity);

        List notExistsList = em.createNativeQuery(checkTable).getResultList();

        AssertSqlCount.assertInsertCount(2);
        AssertSqlCount.assertUpdateCount(0);
        AssertSqlCount.assertSelectCount(5);
        Assert.assertNotNull(bdTableEntity.getId());
        AssertSqlCount.assertDeleteCount(2);


        Assert.assertEquals(existsList.size(), 1);
        Assert.assertEquals(notExistsList.size(), 0);

    }

    @Test
    public void findByParent() {
        VBdObjectEntity entityParent = RootObjects.getTABLE();
        AssertSqlCount.reset();
        List list = tableEntityService.findByParent(entityParent);
        AssertSqlCount.assertSelectCount(2);
        Assert.assertEquals(list.size(), 3);
    }

    @Test
    public void findByCode() {
//        VBdObjectEntity vBdObjectEntityParent = RootObjects.getTABLE();
//        try {
//            vBdTableEntityParent = vBdTableEntityService.findByCode("TABLE");
//        } catch (CoreExeption coreExeption) {
//            coreExeption.printStackTrace();
//            Assert.assertNotNull(null);
//        }
//        Assert.assertNotNull(vBdTableEntityParent);

    }

    @Test
    public void findByTypeObjectCodeIn() {
    }

    @Test
    public void rollBackTest() {
        VBdObjectTypeEntity typeEntity = objectTypeEntityService.findByCode("TABLE");
        VBdObjectEntity vBdObjectEntityParent = RootObjects.getTABLE();
        AssertSqlCount.reset();
        if (vBdObjectEntityParent != null) {
            VBdTableEntity bdTableEntity = new VBdTableEntity();
            String tabName = "!!!!TEST_CODE_TABLE";
            bdTableEntity.setCode(tabName);
            bdTableEntity.setName("NEME " + tabName);
            bdTableEntity.setJavaClass(VBdTableEntity.class.toString());
            bdTableEntity.setParent(vBdObjectEntityParent);
            bdTableEntity.setTypeObject(typeEntity);
            AssertSqlCount.reset();
            try {
                bdTableEntity = tableEntityService.save(bdTableEntity);
            } catch (Exception e) {
                System.out.println(QueryCountInfoHolder.getQueryInfo().getCallSQL());
                System.out.println(QueryCountInfoHolder.getQueryInfo().getDeleteSQL());
                System.out.println(QueryCountInfoHolder.getQueryInfo().getInsertSQL());
                System.out.println(QueryCountInfoHolder.getQueryInfo().getSelectSQL());
                System.out.println(QueryCountInfoHolder.getQueryInfo().getUpdateSQL());
                System.out.println("=======================================");
                e.printStackTrace();
            }

            String checkTable = "select allt.table_name from all_all_tables allt where allt.owner='" + pluginTunes.getOwner() + "' and allt.table_name='" + pluginTunes.getPrefixTable() + tabName + "'";
            Query query = em.createNativeQuery(checkTable);
            List existsList = query.getResultList();

            try {
                bdTableEntity = tableEntityService.findByCode(tabName);
            } catch (CoreExeption coreExeption) {
                bdTableEntity = null;
            }

            try {
                tableEntityService.delete(bdTableEntity);
            } catch (Exception e) {
                System.out.println("====нечего удалять для vBdTableEntityService.delete(bdTableEntity);===================================");
            }

            Assert.assertNull(bdTableEntity);
            Assert.assertEquals(existsList.size(), 0);

//            AssertSqlCount.assertInsertCount(2);
//            AssertSqlCount.assertUpdateCount(0);
//            AssertSqlCount.assertSelectCount(5);
//            Assert.assertNotNull(bdTableEntity.getId());
//            AssertSqlCount.assertDeleteCount(2);
//
//
//            Assert.assertEquals(existsList.size(), 1);
//            Assert.assertEquals(notExistsList.size(), 0);
        }
    }

    @Test
    public void modifyTooLongCode() {
        VBdObjectTypeEntity typeEntity = objectTypeEntityService.findByCode("TABLE");
        VBdObjectEntity vBdObjectEntityParent = RootObjects.getTABLE();
        AssertSqlCount.reset();
        if (vBdObjectEntityParent != null) {
            VBdTableEntity bdTableEntity = new VBdTableEntity();
            //String tabName = "TEST_CODE_TABLE_modifyTooLongCode".toUpperCase();
            String tabName = "TEST_CODE_TABLE_".toUpperCase();
            bdTableEntity.setCode(tabName);
            bdTableEntity.setName("TEST_NAME_TABLE");
            bdTableEntity.setJavaClass(VBdTableEntity.class.toString());
            bdTableEntity.setParent(vBdObjectEntityParent);
            bdTableEntity.setTypeObject(typeEntity);

            bdTableEntity = tableEntityService.save(bdTableEntity);

            tableEntityService.delete(bdTableEntity);
        }

    }
}
