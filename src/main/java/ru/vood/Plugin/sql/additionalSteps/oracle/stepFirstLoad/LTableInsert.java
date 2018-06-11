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

        VBdTableEntity bdObject_table_aderss = new VBdTableEntity();
        bdObject_table_aderss.setCode("address");
        bdObject_table_aderss.setName("Адреса");
        bdObject_table_aderss.setParent(Tables.getTABLE());
        bdObject_table_aderss.setTypeObject(ObjectTypes.getTABLE());
        bdObject_table_aderss.setJavaClass(VBdTableEntity.class.toString());

        bdObject_table_aderss = bdTableEntityService.save(bdObject_table_aderss);

//        for (int i = 0; i <10000 ; i++) {
//            bdObject_table_aderss = new VBdTableEntity();
//            bdObject_table_aderss.setCode("TMP_"+i);
//            bdObject_table_aderss.setName("Временно "+i);
//            bdObject_table_aderss.setParent(Tables.getTABLE());
//            bdObject_table_aderss.setTypeObject(ObjectTypes.getTABLE());
//            bdObject_table_aderss.setJavaClass(VBdTableEntity.class.toString());
//
//            bdObject_table_aderss = bdTableEntityService.save(bdObject_table_aderss);
//        }

        VBdTableEntity bdObject_table_type_adress = new VBdTableEntity();
        bdObject_table_type_adress.setCode("TYPE_ADRESS");
        bdObject_table_type_adress.setName("Типы адресов");
        bdObject_table_type_adress.setParent(Tables.getTABLE());
        bdObject_table_type_adress.setTypeObject(ObjectTypes.getTABLE());
        bdObject_table_type_adress.setJavaClass(VBdTableEntity.class.toString());

        bdObject_table_type_adress = bdTableEntityService.save(bdObject_table_type_adress);


        VBdTableEntity table = new VBdTableEntity();
        table.setCode(bdObject_table_type_adress.getCode() + "_REF");
        table.setName("Ссылка <" + bdObject_table_type_adress.getName() + ">");
        table.setTypeObject(ObjectTypes.getREFERENCE());
        table.setJavaClass(table.getClass().toString());
        table.setParent(Tables.getREFERENCE());
        table.setToType(bdObject_table_type_adress);

        table = bdTableEntityService.save(table);


        table = new VBdTableEntity();
        table.setCode(bdObject_table_aderss.getCode() + "_ARR");
        table.setName("Массив <" + bdObject_table_aderss.getName() + ">");
        table.setTypeObject(ObjectTypes.getARRAY());
        table.setJavaClass(table.getClass().toString());
        table.setParent(Tables.getARRAY());
        table.setToType(bdObject_table_aderss);

        table = bdTableEntityService.save(table);

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
