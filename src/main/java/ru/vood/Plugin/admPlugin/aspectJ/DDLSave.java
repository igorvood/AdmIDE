package ru.vood.Plugin.admPlugin.aspectJ;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.TuneChainStepsCreate;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.impl.ExeptObjectName;

public class DDLSave {

/*
    public static void before(Object joinPoint) {
        System.out.println(joinPoint);
    }*/

    @Deprecated
    public static void checkRun(Object joinPoint, Object o) {
        System.out.println(joinPoint);
        System.out.println(o);
    }

    public static void before(Object joinPoint, Object[] o) {
        Object bdObj = o[0];
        ExeptObjectName exeptObjectName = LoadedCTX.getService(ExeptObjectName.class);

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

    public static void after(Object savedObj, boolean create) {
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
        }
        System.out.println(savedObj);
    }

}
