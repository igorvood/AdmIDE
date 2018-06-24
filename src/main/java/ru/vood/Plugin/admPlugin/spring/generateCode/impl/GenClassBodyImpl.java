package ru.vood.Plugin.admPlugin.spring.generateCode.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.GenCodeCommonFunction;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;
import ru.vood.Plugin.admPlugin.spring.generateCode.intf.GenClassBodyService;
import ru.vood.Plugin.admPlugin.spring.generateCode.intf.GenFieldsService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService;

import java.util.List;

@Component
public class GenClassBodyImpl implements GenClassBodyService {

    @Autowired
    private GenCodeCommonFunction genCodeCommonFunction;

    @Autowired
    private VBdColomnsEntityService colomnsEntityService;

    @Autowired
    private GenFieldsService genFieldsService;

    @Override
    public StringBuilder genCode(VBdTableEntity entity, TypeOfGenClass typeOfGenClass) {
        StringBuilder code = new StringBuilder();
        if (typeOfGenClass == TypeOfGenClass.ENTITY_CLASS) return genCodeEntiy(entity);
        return code;
    }

    private StringBuilder genCodeEntiy(VBdObjectEntity entity) {
        StringBuilder code = new StringBuilder();
        if (genCodeCommonFunction.isRootEntity(entity, TypeOfGenClass.ENTITY_CLASS)) {
            code.append(genCodeCommonFunction.getIdField());
        }

        List<VBdColomnsEntity> colomnsEntities = colomnsEntityService.findByParent((VBdTableEntity) entity);

        for (VBdColomnsEntity colomn : colomnsEntities) {
            code.append(genFieldsService.genCode((VBdTableEntity) entity, TypeOfGenClass.ENTITY_CLASS));
        }

        return code;
    }
}
