package ru.vood.Plugin.admPlugin.aspectJ;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

@Aspect
//@Order(1)
public class DDKCreateAspectJOnJava {

    @Pointcut("execution(* ru.vood.Plugin.admPlugin.spring.intf.*.save(..))")
    //@Pointcut("execution(* ru.vood.Plugin.admPlugin.spring.impl.*.save(..)) ")
    //@Pointcut("execution(* ru.vood.Plugin.dialogs.ADMDialog.addOrEdit(..))")
    public void addOrEditObj() {
    }


    @Around("addOrEditObj()")
    public Object addOrEditObjArround(ProceedingJoinPoint proceedingJoinPoint) {
        //VBdObjectEntity vBdObjectEntity = (VBdObjectEntity) proceedingJoinPoint.getArgs()[0];

        Object[] adding = proceedingJoinPoint.getArgs();
        DDLSave.checkRun(proceedingJoinPoint, adding[0]);
        boolean create = false;
        if (adding[0] instanceof VBdObjectEntity) {
            VBdObjectEntity entities = (VBdObjectEntity) adding[0];
            create = entities.getId() == null ? true : false;
            DDLSave.before(proceedingJoinPoint, adding);
        }
        System.out.println(adding);
        long startTime = System.nanoTime();

        Object ret = null;
        try {
            ret = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
        }
        if (adding[0] instanceof VBdObjectEntity) {
            DDLSave.after(ret, create);
        }
        long endTime = System.nanoTime();
        System.out.println("Method " + proceedingJoinPoint.getSignature().toShortString() + " took " + (endTime - startTime));
        return ret;
    }

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
