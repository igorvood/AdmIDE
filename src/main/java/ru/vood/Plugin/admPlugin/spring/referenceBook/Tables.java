package ru.vood.Plugin.admPlugin.spring.referenceBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.repository.VBdObjectEntityRepository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Service
public class Tables {

    private static Map<String, VBdObjectEntity> bdObjectEntityMap;

    @Autowired
    private VBdObjectEntityRepository objectEntityRepository;

    private static VBdObjectEntity get(String s) {
        VBdObjectEntity entity = bdObjectEntityMap.get(s);
        if (entity == null) {
            VBdObjectEntityRepository objectEntityRepository = LoadedCTX.getService(VBdObjectEntityRepository.class);
            entity = objectEntityRepository.findByCode(s).get(0);
            bdObjectEntityMap.put(entity.getCode(), entity);
        }
        return entity;
    }

    public static VBdObjectEntity getOBJECT() {
        return get("OBJECT");
    }

    public static VBdObjectEntity getDATE() {
        return get("DATE");
    }

    public static VBdObjectEntity getREFERENCE() {
        return get("REFERENCE");
    }

    public static VBdObjectEntity getARRAY() {
        return get("ARRAY");
    }

    public static VBdObjectEntity getSTRING() {
        return get("STRING");
    }

    public static VBdObjectEntity getNUMBER() {
        return get("NUMBER");
    }

    public static VBdObjectEntity getTABLE() {
        return get("TABLE");
    }

    public static VBdObjectEntity getAny(String tableName) {
        return get(tableName);
    }

    @PostConstruct
    private Map<String, VBdObjectEntity> getMainTables() {
        if (bdObjectEntityMap == null) {
            Iterable<VBdObjectEntity> iterable = objectEntityRepository.findAll();
            bdObjectEntityMap = StreamSupport.stream(iterable.spliterator(), false)
                    .collect(Collectors.toMap(p -> p.getCode(), q -> q));
        }
        return bdObjectEntityMap;
    }


}
