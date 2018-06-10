package ru.vood.Plugin.admPlugin.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class LoadedCTX {


    private static EntityManager manager;
    private static ApplicationContext applicationContext;

    public static EntityManager getManager() {
        return manager;
    }

    @Autowired
    private void setManager(EntityManager manager) {
        LoadedCTX.manager = manager;
    }

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
