package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;

@Component
public class LimitingNameDBMS {

    private static int maxLengthNameObject = 30;
    @Autowired
    private CommonFunctionService commonFunction;

    public String getNameObj(String name) {
        if (name.length() <= maxLengthNameObject) {
            return name;
        }
        String nextId = commonFunction.nextId().toString();
        name = name.substring(0, maxLengthNameObject - nextId.length()) + nextId;
        return name;
    }

    public VBdObjectEntity getNameObj(VBdObjectEntity entity) {
        if (entity.getCode().length() <= maxLengthNameObject) {
            return entity;
        }
        if (entity.getId() == null) {
            entity.setId(commonFunction.nextId());
        }
        String nextId = entity.getId().toString();
        String newCode = entity.getCode().substring(0, maxLengthNameObject - nextId.length()) + nextId;
        entity.setCode(newCode);

        return entity;
    }

}
