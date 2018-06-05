package ru.vood.Plugin.admPlugin.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component

public class LoadedCTX {

    private static ApplicationContext applicationContext;

    public static <T> T getService(Class<T> requiredType) {
        //load();
        return applicationContext.getBean(requiredType);
    }

/*    private static void load() {
        if (ctx == null) {
            ctx = new GenericXmlApplicationContext();
            ctx.load("classpath:spring-config.xml"); //move from src.main.java to src.main.resources
            ctx.refresh();
        }
    }*/

    @Autowired
    private void ApplicationContextHolder(ApplicationContext applicationContext) {
        LoadedCTX.applicationContext = applicationContext;
    }
}
