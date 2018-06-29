package ru.vood.Plugin.admPlugin.spring.intf;

import com.jeeconf.hibernate.performancetuning.sqltracker.AssertSqlCount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.vood.Plugin.admPlugin.BaseTest;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;


public class VBdObjectTypeEntityServiceTest extends BaseTest {

    private VBdObjectTypeEntityService vBdObjectTypeEntityService;

    @Before
    public void setVar() {
        vBdObjectTypeEntityService = ctx.getBean(VBdObjectTypeEntityService.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void saveTest() {
        VBdObjectTypeEntity typeEntity = new VBdObjectTypeEntity();
        typeEntity.setCode("TEST_CODE");
        typeEntity.setName("TEST_NAME");
        typeEntity.setNeedDDL(true);

        typeEntity = vBdObjectTypeEntityService.save(typeEntity);
        AssertSqlCount.assertInsertCount(1);
        AssertSqlCount.assertUpdateCount(0);
        AssertSqlCount.assertSelectCount(1);
        Assert.assertNotNull(typeEntity.getId());
        vBdObjectTypeEntityService.delete(typeEntity);
        AssertSqlCount.assertDeleteCount(1);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findByCodeTest() {
        VBdObjectTypeEntity typeEntity = vBdObjectTypeEntityService.findByCode("OBJECT");
        Assert.assertNotNull(typeEntity);

    }
}
