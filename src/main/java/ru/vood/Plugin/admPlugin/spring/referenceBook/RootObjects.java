package ru.vood.Plugin.admPlugin.spring.referenceBook;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.except.ApplicationException;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService;

import java.util.HashMap;
import java.util.Map;

@Repository
@Service
public class RootObjects {

    private static Map<String, VBdObjectEntity> bdObjectEntityMap;

    private static VBdObjectEntity get(String s) {
        if (bdObjectEntityMap == null) {
            bdObjectEntityMap = new HashMap<>();
        }
        VBdObjectEntity entity = bdObjectEntityMap.get(s);
        if (entity == null) {
            VBdObjectEntityService tableEntityService = LoadedCTX.getService(VBdObjectEntityService.class);
            try {
                entity = tableEntityService.findByCode(s);
                bdObjectEntityMap.put(entity.getCode(), entity);
            } catch (ApplicationException e) {
                entity = null;
            }
        }
        return entity;
    }


    public static VBdObjectEntity getOBJECT() {
        return get("OBJECT");
    }

    public static VBdObjectEntity getDATE() {
        return get("DATE");
    }

    public static VBdObjectEntity getBOOLEAN() {
        return get("BOOLEAN");
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

}
