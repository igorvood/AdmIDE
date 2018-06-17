package ru.vood.Plugin.admPlugin.aspectJ;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.except.ApplicationException;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService;

@Aspect
//@Component
//@Order(1)
public class DDKCreateAspectJOnJava {

    @Pointcut("execution(* ru.vood.Plugin.admPlugin.spring.intf.*.save(..))")
    // @Pointcut("execution(* ru.vood.Plugin.admPlugin.spring.impl.*.save(..)) ")
    //@Pointcut("execution(* ru.vood.Plugin.dialogs.ADMDialog.addOrEditColomn(..))")
    public void addOrEditObj() {
    }


    @Around("addOrEditObj()")
    public Object addOrEditObjArround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object[] adding = proceedingJoinPoint.getArgs();
        DDLSave.checkRun(proceedingJoinPoint, adding[0]);
        boolean create = false;
        VBdObjectEntity newEntity = null;
        VBdObjectEntity oldEntity = null;
        if (adding[0] instanceof VBdObjectEntity) {
            newEntity = (VBdObjectEntity) adding[0];
            create = newEntity.getId() == null ? true : false;
            if (!create) {
                oldEntity = (VBdObjectEntity) LoadedCTX.getService(VBdObjectEntityService.class).findOne(newEntity.getId()).copy();
            }
            DDLSave.before(proceedingJoinPoint, adding, oldEntity);
        }
        //System.out.println(adding);

        // ========================================= Вызов основного метода==========================================
        Object ret;
        try {
            ret = proceedingJoinPoint.proceed(adding);
        } catch (Throwable throwable) {
            DDLSave.error(throwable);
            ret = null;
            throw new ApplicationException("Не удалось выполнить сохрание ", throwable);
        }
        // ========================================= Вызов основного метода==========================================
        if (ret != null) {
            if (adding[0] instanceof VBdObjectEntity) {
                try {
                    DDLSave.after(ret, create, oldEntity);
                } catch (Exception e) {
                    ret = null;
                }
            }
        }
/*        if (ret != null) {
            try {
                connection.commit();
            } catch (SQLException e) {

            }
        } else {
            try {
                connection.rollback();
            } catch (SQLException e) {

            }
        }

        try {
            connection.close();
        } catch (SQLException e) {

        }*/


        long endTime = System.nanoTime();
        System.out.println("Method " + proceedingJoinPoint.getSignature().toShortString() + " took " + (endTime - startTime));
        return ret;
    }

//    private void after(Object savedObj, boolean create, Object oldObj) {
//        if (create & savedObj != null) {
//            if (savedObj instanceof VBdObjectEntity) {
//                VBdObjectEntity entity = (VBdObjectEntity) savedObj;
//                if (entity.getTypeObject().isNeedDDL()) {
//                    ExeptObjectName exeptObjectName = LoadedCTX.getService(ExeptObjectName.class);
//                    if (exeptObjectName.allowAdd(entity.getCode())) {
//                        TuneChainStepsCreate create1 = LoadedCTX.getService(TuneChainStepsCreate.class);
//                        create1.runChain(savedObj);
//                    }
//                }
//            }
//        } else if (!create) {
//            if (savedObj instanceof VBdObjectEntity && oldObj instanceof VBdObjectEntity) {
//                VBdTableEntity bdTableOld = (VBdTableEntity) oldObj;
//                VBdTableEntity bdTableNew = (VBdTableEntity) savedObj;
//                if (bdTableNew.getTypeObject().isNeedDDL()) {
//                    ExeptObjectName exeptObjectName = LoadedCTX.getService(ExeptObjectName.class);
//                    if (exeptObjectName.allowAdd(bdTableNew.getCode())) {
//
//                    }
//                }
//            }
//        }
//        System.out.println(savedObj);
//    }

//    @Pointcut("execution(* ru.vood.Plugin.admPlugin.spring.entity.ParentForAll.save(..))")
//    public void save() {
//    }
//
//    @Around("save()")
//    public Object saveArround(ProceedingJoinPoint proceedingJoinPoint)  {
//
//        Object ret = null;
//        try {
//            ret = proceedingJoinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//
//        return ret;
//    }


}
