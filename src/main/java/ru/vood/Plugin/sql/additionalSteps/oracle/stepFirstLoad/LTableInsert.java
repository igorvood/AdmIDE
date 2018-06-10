package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.spring.referenceBook.Tables;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.QueryTableNew;

@Service
public class LTableInsert {


    @Autowired
    @Qualifier("jpaVBdTableEntityService")
    private VBdTableEntityService bdTableEntityService;

    public QueryTableNew additionOne() {

        VBdObjectTypeEntity bdObjType_TABLE = ObjectTypes.getDATE();

        VBdObjectEntity bdObj_DATE = Tables.getDATE();

        VBdTableEntity bdObject_date_table = new VBdTableEntity();
        bdObject_date_table.setCode("DATE");
        bdObject_date_table.setName("Дата");
        bdObject_date_table.setParent(bdObj_DATE);
        bdObject_date_table.setTypeObject(bdObjType_TABLE);
        bdObject_date_table.setJavaClass(VBdTableEntity.class.toString());

        VBdTableEntity newTableEntity = bdTableEntityService.save(bdObject_date_table);

        return null;
    }
}
