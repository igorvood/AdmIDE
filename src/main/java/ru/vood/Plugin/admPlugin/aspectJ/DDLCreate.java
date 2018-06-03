package ru.vood.Plugin.admPlugin.aspectJ;

import org.aspectj.lang.ProceedingJoinPoint;

public class DDLCreate {

    public static void before(Object joinPoint) {
        System.out.println(joinPoint);
    }

    public static void before(Object joinPoint, Object o) {
        System.out.println(joinPoint);
        System.out.println(o);
    }

    public static void after(ProceedingJoinPoint joinPoint) {

    }

}
