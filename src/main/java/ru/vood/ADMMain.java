package ru.vood;

import com.google.gson.Gson;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import ru.vood.Plugin.admPlugin.spring.gson.GsonTune;
import ru.vood.Plugin.admPlugin.tune.Configarations;
import ru.vood.Plugin.dialogs.ADMDialog;
import ru.vood.Plugin.dialogs.ADMTuneDialog;
import ru.vood.Plugin.dialogs.MessageWin;
import ru.vood.core.runtime.exception.CoreRuntimeException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.vood.Plugin.applicationConst.AppConst.CONNECTIONS;

public class ADMMain {

    private static GenericXmlApplicationContext ctx;


    public ADMMain() {
        ADMDialog admDialog = new ADMDialog();
        admDialog.pack();
        admDialog.setVisible(true);
    }


    private static void init() {
        Gson gson = GsonTune.getGson();

        StringBuffer gsonStr = new StringBuffer();
        Configarations tunesList = new Configarations();
        try {
            Files.lines(Paths.get(CONNECTIONS), StandardCharsets.UTF_8).forEach((s1) -> {
                gsonStr.append(s1);
            });
            tunesList = gson.fromJson(gsonStr.toString(), tunesList.getClass());
        } catch (IOException e) {

        }

        ADMTuneDialog dialog = new ADMTuneDialog(tunesList);
        dialog.pack();
        dialog.setVisible(true);
    }

    ;

    public static void main(String[] args) {

        //init();
        ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring-config.xml"); //move from src.main.java to src.main.resources
        ctx.refresh();

        System.out.println("----------------------------------------");
        DriverManagerDataSource dataSource = ADMMain.getCtx().getBean(DriverManagerDataSource.class);
        System.out.println(dataSource + " - " + dataSource.getUrl());

        LocalContainerEntityManagerFactoryBean factoryBean = ADMMain.getCtx().getBean(LocalContainerEntityManagerFactoryBean.class);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        factoryBean.getJpaPropertyMap().entrySet().stream().peek(e -> System.out.println(e.getKey() + " -" + e.getValue()));

        System.out.println("----------------------------------------");
        try {
            new ADMMain();
        } catch (CoreRuntimeException e) {
            new MessageWin(e.toString());
        }

//        try {
//            DBConnect.closeConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


    public static GenericXmlApplicationContext getCtx() {
        return ctx;
    }

}
