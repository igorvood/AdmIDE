package ru.vood.Plugin.admPlugin.aspectJ;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
//@Order(1)
public class DDKCreateAspectJOnJava {

    @Pointcut("execution(* ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService.save(..))")
    //@Pointcut("execution(* ru.vood.Plugin.dialogs.ADMDialog.addOrEdit(..))")
    public void addOrEditObj() {
    }


    @Around("addOrEditObj()")
    public Object addOrEditObjArround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //VBdObjectEntity vBdObjectEntity = (VBdObjectEntity) proceedingJoinPoint.getArgs()[0];

        Object[] adding = proceedingJoinPoint.getArgs();
        DDLSave.before(proceedingJoinPoint, adding);
        System.out.println(adding);

        long startTime = System.nanoTime();
        Object ret = proceedingJoinPoint.proceed();

        DDLSave.after(adding[0], ret);
        if (ret != null) {

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
