package ru.vood.Plugin.admPlugin.aspectJ;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.sql.ExeptObjectName;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.TuneChainStepsCreate;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToEdit.TuneChainStepsEdit;

public class DDLSave {


    public static void checkRun(Object joinPoint, Object o) {
        System.out.println(joinPoint);
        System.out.println(o);
    }

    public static void before(Object joinPoint, Object[] o, VBdObjectEntity objectEntity) {
        Object bdObj = o[0];
        ExeptObjectName exeptObjectName = LoadedCTX.getService(ExeptObjectName.class);
        System.out.println(objectEntity);
        if (bdObj instanceof VBdObjectEntity) {
            VBdObjectEntity entity = (VBdObjectEntity) bdObj;
            if (exeptObjectName.allowAdd(entity.getCode())) {

            }
           /* VBdObjectEntity entity = (VBdObjectEntity) bdObj;
            if (entity.getId() == null) {
                CommonFunctionService commonFunctionService = LoadedCTX.getService(CommonFunctionService.class);
                entity.setId(commonFunctionService.nextId());
            }
            if (entity.getDateCreate() == null) {
                entity.setDateCreate(new Date());
            }*/
        }
        System.out.println(joinPoint);
        System.out.println(o);
    }

    public static void after(Object savedObj, boolean create, Object oldObj) {
        if (create & savedObj != null) {
            if (savedObj instanceof VBdObjectEntity) {
                VBdObjectEntity entity = (VBdObjectEntity) savedObj;
                if (entity.getTypeObject().isNeedDDL()) {
                    ExeptObjectName exeptObjectName = LoadedCTX.getService(ExeptObjectName.class);
                    if (exeptObjectName.allowAdd(entity.getCode())) {
                        TuneChainStepsCreate create1 = LoadedCTX.getService(TuneChainStepsCreate.class);
                        create1.runChain(savedObj);
                    }
                }
            }
        } else if (!create) {
            if (savedObj instanceof VBdObjectEntity && oldObj instanceof VBdObjectEntity) {
                VBdTableEntity bdTableOld = (VBdTableEntity) oldObj;
                VBdTableEntity bdTableNew = (VBdTableEntity) savedObj;
                if (bdTableNew.getTypeObject().isNeedDDL()) {
                    ExeptObjectName exeptObjectName = LoadedCTX.getService(ExeptObjectName.class);
                    if (exeptObjectName.allowAdd(bdTableNew.getCode())) {
                        TuneChainStepsEdit stepsEdit = LoadedCTX.getService(TuneChainStepsEdit.class);
                        stepsEdit.runChain(oldObj, savedObj);
                    }
                }
            }
        }
        System.out.println(savedObj);
    }

}
