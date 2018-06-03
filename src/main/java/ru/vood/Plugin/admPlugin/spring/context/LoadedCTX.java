package ru.vood.Plugin.admPlugin.spring.context;

import org.springframework.context.support.GenericXmlApplicationContext;

public class LoadedCTX {

    private static GenericXmlApplicationContext ctx;

    private static void load() {
        if (ctx == null) {
            ctx = new GenericXmlApplicationContext();
            ctx.load("classpath:spring-config.xml"); //move from src.main.java to src.main.resources
            ctx.refresh();
        }
    }

    public static <T> T getService(Class<T> requiredType) {
        load();
        return ctx.getBean(requiredType);
    }
}
