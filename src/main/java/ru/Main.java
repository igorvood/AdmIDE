package ru;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.vood.Plugin.admPlugin.gson.GsonTune;
import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.except.NoDataFoundException;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectTypeEntityService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static GenericXmlApplicationContext ctx;

    public static GenericXmlApplicationContext getCtx() {
        return ctx;
    }

    public static void main(String[] args) {

        VBdObjectTypeEntity typeEntity_9;
        typeEntity_9 = entityService.findByCode("TABLE");

        //List<VBdObjectTypeEntity> typeEntities = new ArrayList<>();
        VBdObjectTypeEntity typeEntity = null;
        VBdObjectTypeEntity prevTypeEntity;
        for (int i = 9; i >= 0; i--) {
            try {
                typeEntity = entityService.findByCode("CODE_" + i);
                System.out.println("================================");
            } catch (NoDataFoundException e) {
                prevTypeEntity = typeEntity;
                typeEntity = new VBdObjectTypeEntity();
//                typeEntity.setId(entityService.nextId());
                typeEntity.setCode("CODE_" + i);
                typeEntity.setName("NAME_" + i);
                typeEntity.setParent(prevTypeEntity);
                typeEntity = typeEntity.save();
            }
        }



        /*--- Успешные тесты-----------------------*/
        ParentForAll.getServise(VBdObjectTypeEntity.class);
        VBdObjectTypeEntityService service = ctx.getBean("jpaVBdObjectTypeEntityService", VBdObjectTypeEntityService.class);

        String[] strings = {"TABLE", "REFERENCE", "ARRAY", "STRING", "NUMBER", "DATE", "BOOLEAN"};

        List<VBdObjectTypeEntity> typeEntities1 = service.findByCodeIn(strings);
        System.out.println(typeEntities1.toString());
        VBdObjectTypeEntity[] vBdObjectTypeEntities = new VBdObjectTypeEntity[]{new VBdObjectTypeEntity(), new VBdObjectTypeEntity()};
        System.out.println("=====================================================");

        VBdObjectEntity objectEntity = new VBdObjectEntity();
        VBdObjectEntityService vBdObjectEntityService = objectEntity.getServise();
        List<VBdObjectEntity> vBdObjectEntities = vBdObjectEntityService.findByTypeObjectCodeIn(strings);
        System.out.println(vBdObjectEntities.toString());
        System.out.println("=====================================================");
        VBdTableEntity vBdTableEntity = new VBdTableEntity();
        VBdTableEntityService vBdTableEntityService = vBdTableEntity.getServise();
        objectEntity = vBdTableEntityService.findOne(BigDecimal.valueOf(4682130769L));
        System.out.println("---" + objectEntity);
        System.out.println("!!!!!!");
        vBdTableEntity = vBdTableEntityService.findOne(BigDecimal.valueOf(4682130778L));
        System.out.println("---" + vBdTableEntity);
        System.out.println("=====================================================");

        /*--- Успешные тесты-----------------------*/
        System.exit(0);
    }

    private static void printAll(List<VBdObjectTypeEntity> contacts) {
        System.out.println("printAll: ");
        for (VBdObjectTypeEntity contact : contacts) {
            //System.out.println(contact.getId() + " " + contact.getCode() + " " + contact.getName() + " " + contact.getParent());
            System.out.println(contact.toString());
        }
    }

    private static void printAll1(List<VBdObjectEntity> contacts) {
        System.out.println("printAll: ");
        for (VBdObjectEntity contact : contacts) {
            //System.out.println(contact.getId() + " " + contact.getCode() + " " + contact.getName() + " " + contact.getParent());
            System.out.println(contact.toString());
        }
    }

    public static void gson(ArrayList<VBdObjectEntity> vBdObjectEntities) {

        Gson gson = GsonTune.getGson();
        String s = gson.toJson(vBdObjectEntities);

        Type type = new TypeToken<ArrayList<VBdObjectEntity>>() {
        }.getType();
        ArrayList<VBdObjectEntity> entities = gson.fromJson(s, type);
    }




  /*  @Bean
    public CommandLineRunner demo(VBdObjectTypeEntityRepository repository) {
        return (args) -> {repository.save(new VBdObjectTypeEntity());
        }
    }*/
}

