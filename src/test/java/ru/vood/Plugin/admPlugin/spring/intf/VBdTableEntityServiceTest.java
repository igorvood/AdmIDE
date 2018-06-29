package ru.vood.Plugin.admPlugin.spring.intf;

import com.jeeconf.hibernate.performancetuning.sqltracker.AssertSqlCount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.vood.Plugin.admPlugin.BaseTest;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

import javax.persistence.Query;
import java.util.List;


public class VBdTableEntityServiceTest extends BaseTest {

    private VBdTableEntityService vBdTableEntityService;

    private VBdObjectTypeEntityService vBdObjectTypeEntityService;

    private PluginTunes pluginTunes;

    @Before
    public void setVar() {
        vBdTableEntityService = ctx.getBean(VBdTableEntityService.class);
        vBdObjectTypeEntityService = ctx.getBean(VBdObjectTypeEntityService.class);
        pluginTunes = ctx.getBean(PluginTunes.class);
    }

    @Test
    public void saveTest() {

        VBdObjectTypeEntity typeEntity = vBdObjectTypeEntityService.findByCode("TABLE");
        VBdObjectEntity vBdObjectEntityParent = RootObjects.getTABLE();
        AssertSqlCount.reset();
        if (vBdObjectEntityParent != null) {
            VBdTableEntity bdTableEntity = new VBdTableEntity();
            String tabName = "TEST_CODE_TABLE";
            bdTableEntity.setCode(tabName);
            bdTableEntity.setName("TEST_NAME_TABLE");
            bdTableEntity.setJavaClass(VBdTableEntity.class.toString());
            bdTableEntity.setParent(vBdObjectEntityParent);
            bdTableEntity.setTypeObject(typeEntity);

            bdTableEntity = vBdTableEntityService.save(bdTableEntity);

            String checkTable = "select allt.table_name from all_all_tables allt where allt.owner='" + pluginTunes.getOwner() + "' and allt.table_name='" + pluginTunes.getPrefixTable() + tabName + "'";
            Query query = em.createNativeQuery(checkTable);
            List existsList = query.getResultList();

            vBdTableEntityService.delete(bdTableEntity);

            List notExistsList = em.createNativeQuery(checkTable).getResultList();

            AssertSqlCount.assertInsertCount(2);
            AssertSqlCount.assertUpdateCount(0);
            AssertSqlCount.assertSelectCount(7);
            Assert.assertNotNull(bdTableEntity.getId());
            AssertSqlCount.assertDeleteCount(2);


            Assert.assertEquals(existsList.size(), 1);
            Assert.assertEquals(notExistsList.size(), 0);
        }

    }

    @Test
    public void delete() {
    }

    @Test
    public void findByParent() {
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
}
