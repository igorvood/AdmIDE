package ru.vood.Plugin.admPlugin.aspectJ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.except.ApplicationException;
import ru.vood.Plugin.admPlugin.sql.ExeptObjectName;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.LimitingNameDBMS;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.TuneChainStepsCreateServise;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToDrop.TuneChainStepsDrop;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.TuneChainStepsEdit;

@Service
public class DDLSave {

    @Autowired
    private TuneChainStepsCreateServise tuneChainStepsCreateServise;

    @Autowired
    private TuneChainStepsDrop tuneChainStepsDrop;

    @Autowired
    private TuneChainStepsEdit tuneChainStepsEdit;

    @Autowired
    private ExeptObjectName exeptObjectName;

    @Autowired
    private LimitingNameDBMS limitingNameDBMS;

    public void checkRun(Object joinPoint, Object o) {
        if (o instanceof VBdObjectEntity) {
            beforeTest((VBdObjectEntity) o);
        }
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

    public void afterSave(Object savedObj, boolean create, Object oldObj) {
        if (create & savedObj != null) {
            if (savedObj instanceof VBdObjectEntity) {
                VBdObjectEntity entity = (VBdObjectEntity) savedObj;
                if (entity.getTypeObject().isNeedDDL()) {
                    if (exeptObjectName.allowAdd(entity.getCode())) {
                        try {
                            tuneChainStepsCreateServise.runChain(entity);
                        } catch (Exception e) {
                            throw new ApplicationException(e.getMessage(), e);
                        }
                    }
                }
            }
        } else if (!create) {
            if (savedObj instanceof VBdObjectEntity && oldObj instanceof VBdObjectEntity) {
                VBdObjectEntity bdTableOld = (VBdObjectEntity) oldObj;
                VBdObjectEntity bdTableNew = (VBdObjectEntity) savedObj;
                if (bdTableNew.getTypeObject().isNeedDDL()) {
                    if (exeptObjectName.allowAdd(bdTableNew.getCode())) {
                        tuneChainStepsEdit.runChain(bdTableOld, bdTableNew);
                    }
                }
            }
        }
        System.out.println(savedObj);
    }

    public void afterDrop(Object[] dropedObj) {
        Object d = dropedObj[0];
        if (d instanceof VBdObjectEntity) {
            VBdObjectEntity entity = (VBdObjectEntity) d;
            if (entity.getTypeObject().isNeedDDL()) {
                tuneChainStepsDrop.runChain(entity);
            }
        }
    }

    public void error(Throwable throwable) {
        throwable.printStackTrace();
    }

    public void beforeTest(VBdObjectEntity o) {
        limitingNameDBMS.getNameObj(o);
    }
}
