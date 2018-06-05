package ru.vood.Plugin.admPlugin.aspectJ;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;

public class DDLSave {

/*
    public static void before(Object joinPoint) {
        System.out.println(joinPoint);
    }*/

    public static void before(Object joinPoint, Object[] o) {
        Object bdObj = o[0];
        if (bdObj instanceof VBdObjectEntity) {
            CommonFunctionService commonFunctionService = LoadedCTX.getService(CommonFunctionService.class);

            ((VBdObjectEntity) bdObj).setId(commonFunctionService.nextId());
            //    TuneChainStepsCreate.runChain(o,((VBdObjectEntity) bdObj).getJavaClass());
        }
        System.out.println(joinPoint);
        System.out.println(o);
    }

    public static void after(Object beforeSaving, Object afterSaving) {
        System.out.println(beforeSaving);
        System.out.println(afterSaving);

    }

}
