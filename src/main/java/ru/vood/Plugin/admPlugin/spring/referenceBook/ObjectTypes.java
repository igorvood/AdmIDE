package ru.vood.Plugin.admPlugin.spring.referenceBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.repository.VBdObjectTypeEntityRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class ObjectTypes {

    private static Map<String, VBdObjectTypeEntity> objectTypeEntityMap;

    @Autowired
    private VBdObjectTypeEntityRepository objectTypeEntityRepository;

    private static VBdObjectTypeEntity get(String s) {
        VBdObjectTypeEntity entity;
        if (objectTypeEntityMap == null) {
            objectTypeEntityMap = new HashMap<>();
        }
        entity = objectTypeEntityMap.get(s);
        if (entity == null) {
            VBdObjectTypeEntityRepository vBdObjectTypeEntityRepository = LoadedCTX.getService(VBdObjectTypeEntityRepository.class);
            entity = vBdObjectTypeEntityRepository.findByCode(s).get(0);
            objectTypeEntityMap.put(entity.getCode(), entity);
        }

        return entity;
    }

    public static VBdObjectTypeEntity getTABLE() {
        return get("TABLE");
    }

    public static VBdObjectTypeEntity getREFERENCE() {
        return get("REFERENCE");
    }

    public static VBdObjectTypeEntity getSTRING() {
        return get("STRING");
    }

    public static VBdObjectTypeEntity getNUMBER() {
        return get("NUMBER");
    }

    public static VBdObjectTypeEntity getDATE() {
        return get("DATE");
    }

    public static VBdObjectTypeEntity getBOOLEAN() {
        return get("BOOLEAN");
    }

    public static VBdObjectTypeEntity getCOLOMN() {
        return get("COLOMN");
    }

    public static VBdObjectTypeEntity getARRAY() {
        return get("ARRAY");
    }

    public static VBdObjectTypeEntity getINDEX() {
        return get("INDEX");
    }

    public static VBdObjectTypeEntity getOBJECT() {
        return get("OBJECT");
    }



   /* @PostConstruct
    private Map<String, VBdObjectTypeEntity> getObls() {
        if (objectTypeEntityMap == null) {
            try {
                Iterable<VBdObjectTypeEntity> iterable = objectTypeEntityRepository.findAll();
                objectTypeEntityMap = StreamSupport.stream(iterable.spliterator(), false)
                        .collect(Collectors.toMap(p -> p.getCode(), q -> q));
            } catch (Exception e){
                System.out.println("Скорей всего не загруженны таблицы");
            }
        }
        return objectTypeEntityMap;
    }*/


}
