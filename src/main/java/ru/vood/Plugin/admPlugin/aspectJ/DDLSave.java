package ru.vood.Plugin.admPlugin.aspectJ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.sql.ExeptObjectName;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.TuneChainStepsCreateServise;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.TuneChainStepsEdit;

@Service
public class DDLSave {

    @Autowired
    @Qualifier("tuneChainStepsCreate")
    private TuneChainStepsCreateServise tuneChainStepsCreateServise;

    @Autowired
    private TuneChainStepsEdit tuneChainStepsEdit;

    @Autowired
    private ExeptObjectName exeptObjectName;

    public void checkRun(Object joinPoint, Object o) {
        System.out.println(joinPoint);
        System.out.println(o);
    }

    public void before(Object joinPoint, Object[] o, VBdObjectEntity objectEntity) {
        Object bdObj = o[0];
        System.out.println(objectEntity);
        if (bdObj instanceof VBdObjectEntity) {
            VBdObjectEntity entity = (VBdObjectEntity) bdObj;
            if (exeptObjectName.allowAdd(entity.getCode())) {

            }
        }
        System.out.println(joinPoint);
        System.out.println(o);
    }

    public void after(Object savedObj, boolean create, Object oldObj) {
        if (create & savedObj != null) {
            if (savedObj instanceof VBdObjectEntity) {
                VBdObjectEntity entity = (VBdObjectEntity) savedObj;
                if (entity.getTypeObject().isNeedDDL()) {
                    if (exeptObjectName.allowAdd(entity.getCode())) {
                        tuneChainStepsCreateServise.runChain(savedObj);
                    }
                }
            }
        } else if (!create) {
            if (savedObj instanceof VBdObjectEntity && oldObj instanceof VBdObjectEntity) {
                VBdTableEntity bdTableOld = (VBdTableEntity) oldObj;
                VBdTableEntity bdTableNew = (VBdTableEntity) savedObj;
                if (bdTableNew.getTypeObject().isNeedDDL()) {
                    if (exeptObjectName.allowAdd(bdTableNew.getCode())) {
                        tuneChainStepsEdit.runChain(bdTableOld, savedObj);
                    }
                }
            }
        }
        System.out.println(savedObj);
    }

    public void error(Throwable throwable) {
        throwable.printStackTrace();
    }

}
