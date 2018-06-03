package ru.vood.Plugin.admPlugin.spring.referenceBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.repository.VBdObjectTypeEntityRepository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Service
public class ObjectTypes {

    private static Map<String, VBdObjectTypeEntity> objectTypeEntityMap;
    @Autowired
    private VBdObjectTypeEntityRepository objectTypeEntityRepository;

    private static VBdObjectTypeEntity get(String s) {
        VBdObjectTypeEntity entity = objectTypeEntityMap.get(s);
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

    @PostConstruct
    private Map<String, VBdObjectTypeEntity> getObls() {
        if (objectTypeEntityMap == null) {
            Iterable<VBdObjectTypeEntity> iterable = objectTypeEntityRepository.findAll();
            objectTypeEntityMap = StreamSupport.stream(iterable.spliterator(), false)
                    .collect(Collectors.toMap(p -> p.getCode(), q -> q));

        }
        return objectTypeEntityMap;
    }

}
