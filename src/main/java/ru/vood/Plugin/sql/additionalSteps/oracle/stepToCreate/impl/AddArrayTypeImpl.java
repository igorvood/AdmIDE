package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.*;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdIndexEntityService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdIndexedColomnsEntityService;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.spring.referenceBook.Tables;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.sql.QueryTableNew;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;
import ru.vood.Plugin.sql.dbms.oracle.AddIndexSql;

import static ru.vood.Plugin.sql.sqlInterfaces.SQLInterface.COLLECTION;

@Component
public class AddArrayTypeImpl implements StepsCreateServise {

    // todo сюда добавить ссылок
//    @Autowired
//    @Qualifier("addArrayTypeImpl")
//    private StepsCreateServise nextStep;

    @Autowired
    private CommonFunctionService commonFunction;

    @Autowired
    private PluginTunes pluginTunes;

    @Autowired
    private AddIndexSql addIndexSql;

    @Autowired
    @Qualifier("jpaVBdColomnsEntityService")
    private VBdColomnsEntityService colomnsEntityService;

    @Autowired
    @Qualifier("jpaVBdIndexEntityService")
    private VBdIndexEntityService indexEntityService;

    @Autowired
    @Qualifier("jpaVBdIndexedColomnsService")
    private VBdIndexedColomnsEntityService indexedColomnsEntityService;


    @Override
    public QueryTableNew createDDL(VBdObjectEntity bdObject) {
        if (!(bdObject instanceof VBdTableEntity)) {
            return null;
        }


        VBdTableEntity bdTable = (VBdTableEntity) bdObject;
        QueryTableNew queryTable = null;
        if (bdTable.getTypeObject().equals(ObjectTypes.getARRAY())) {
            queryTable = new QueryTableNew();

            VBdColomnsEntity colomnsEntity = new VBdColomnsEntity();
            colomnsEntity.setParent(((VBdTableEntity) bdObject).getToType());
            colomnsEntity.setCode(COLLECTION);
            colomnsEntity.setName(COLLECTION);
            colomnsEntity.setNotNull("1");
            colomnsEntity.setTypeColomn(ObjectTypes.getNUMBER());
            colomnsEntity.setTypeValue(Tables.getAny("NUM"));
            colomnsEntity.setTypeObject(ObjectTypes.getCOLOMN());
            colomnsEntity.setJavaClass(colomnsEntity.getClass().toString());
            VBdColomnsEntity new_colomnsEntity = colomnsEntityService.save(colomnsEntity);

            VBdIndexEntity indexEntity = new VBdIndexEntity();
            indexEntity.setCode("IDX_" + bdObject.getCode() + "_" + colomnsEntity.getCode());
            indexEntity.setName("IDX_" + bdObject.getCode() + "_" + colomnsEntity.getCode());
            indexEntity.setTypeObject(ObjectTypes.getINDEX());
            indexEntity.setParent(((VBdTableEntity) bdObject).getToType());
            indexEntity.setJavaClass(indexEntity.getClass().toString());
            indexEntity.setColumns(commonFunction.nextId());
            indexEntity = indexEntityService.save(indexEntity);

            VBdIndexedColomnsEntity indexedColomnsEntity = new VBdIndexedColomnsEntity();
            indexedColomnsEntity.setCollectionId(indexEntity.getColumns());
            indexedColomnsEntity.setColomnRef(colomnsEntity);

            indexedColomnsEntityService.save(indexedColomnsEntity);

            //String tmp = SQLFactory.getInstance().getSQLForAddCollectionId(bdTable.getToType().getCode());
//            queryTable.add(tmp);
//
//            tmp = addIndexSql.generateSys(pluginTunes.getPrefixTable() + bdTable.getToType().getCode(), SQLInterface.COLLECTION);
//            queryTable.add(tmp);


        }

        return queryTable;
    }

    @Override
    public StepsCreateServise getNextStep() {
        return null;
    }
}
