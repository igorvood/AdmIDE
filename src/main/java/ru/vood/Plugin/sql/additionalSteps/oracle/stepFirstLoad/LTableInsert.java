package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.spring.referenceBook.Tables;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.QueryTableNew;

@Service
public class LTableInsert {


    @Autowired
    @Qualifier("jpaVBdTableEntityService")
    private VBdTableEntityService bdTableEntityService;


    @Autowired
    @Qualifier("jpaVBdColomnsEntityService")
    private VBdColomnsEntityService colomnsEntityService;

    public QueryTableNew additionOne() {

        VBdTableEntity bdObject_date_table = new VBdTableEntity();
        bdObject_date_table.setCode("DATE");
        bdObject_date_table.setName("Дата");
        bdObject_date_table.setParent(Tables.getDATE());
        bdObject_date_table.setTypeObject(ObjectTypes.getDATE());
        bdObject_date_table.setJavaClass(VBdTableEntity.class.toString());

        VBdTableEntity newTableEntity = bdTableEntityService.save(bdObject_date_table);

        VBdTableEntity bdObject_str_table = new VBdTableEntity();
        bdObject_str_table.setCode("STR_160");
        bdObject_str_table.setName("Стока(160)");
        bdObject_str_table.setParent(Tables.getSTRING());
        bdObject_str_table.setTypeObject(ObjectTypes.getSTRING());
        bdObject_str_table.setLength(160L);
        bdObject_str_table.setJavaClass(VBdTableEntity.class.toString());

        VBdTableEntity bdObject_str_table_new = bdTableEntityService.save(bdObject_str_table);


        VBdTableEntity bdObject_num_table = new VBdTableEntity();
        bdObject_num_table.setCode("NUM_17_2");
        bdObject_num_table.setName("Число(17,2)");
        bdObject_num_table.setParent(Tables.getNUMBER());
        bdObject_num_table.setTypeObject(ObjectTypes.getNUMBER());
        bdObject_num_table.setLength(17L);
        bdObject_num_table.setPrecision(2L);
        bdObject_num_table.setJavaClass(VBdTableEntity.class.toString());

        VBdTableEntity bdObject_num_table_new = bdTableEntityService.save(bdObject_num_table);


        VBdTableEntity bdObject_table = new VBdTableEntity();
        bdObject_table.setCode("CLIENT");
        bdObject_table.setName("Клиенты");
        bdObject_table.setParent(Tables.getTABLE());
        bdObject_table.setTypeObject(ObjectTypes.getTABLE());
        bdObject_table.setJavaClass(VBdTableEntity.class.toString());

        VBdTableEntity bdObject_table_new = bdTableEntityService.save(bdObject_table);

        bdObject_table = new VBdTableEntity();
        bdObject_table.setCode("address");
        bdObject_table.setName("Адреса");
        bdObject_table.setParent(Tables.getTABLE());
        bdObject_table.setTypeObject(ObjectTypes.getTABLE());
        bdObject_table.setJavaClass(VBdTableEntity.class.toString());

        bdObject_table_new = bdTableEntityService.save(bdObject_table);

//        VBdColomnsEntity colomnsEntity = new VBdColomnsEntity();
//        colomnsEntity.setCode("NAME");
//        colomnsEntity.setName("ФИО");
//        colomnsEntity.setTypeColomn(ObjectTypes.getSTRING());
//        colomnsEntity.setTypeValue(bdObject_str_table_new);
//        colomnsEntity.setParent(bdObject_table_new);
//        bdObject_table.setJavaClass(VBdColomnsEntity.class.toString());
//        colomnsEntity=colomnsEntityService.save(colomnsEntity);


        return null;
    }
}
