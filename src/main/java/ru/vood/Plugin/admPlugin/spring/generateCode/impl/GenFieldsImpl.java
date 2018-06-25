package ru.vood.Plugin.admPlugin.spring.generateCode.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.GenCodeCommonFunction;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;
import ru.vood.Plugin.admPlugin.spring.generateCode.intf.GenAnnitationFieldsService;
import ru.vood.Plugin.admPlugin.spring.generateCode.intf.GenFieldsService;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;

@Component
@Deprecated
public class GenFieldsImpl implements GenFieldsService {

    @Autowired
    private GenCodeCommonFunction genCodeCommonFunction;

    @Autowired
    private GenAnnitationFieldsService genAnnitationFieldsService;

    @Override
    public StringBuilder genCode(VBdObjectEntity entity, TypeOfGenClass typeOfGenClass) {
        StringBuilder code = new StringBuilder();
        if (typeOfGenClass.equals(TypeOfGenClass.ENTITY_CLASS)) {
            VBdColomnsEntity bdColomnsEntity = (VBdColomnsEntity) entity;
            code.append(genAnnitationFieldsService.genCode(entity, typeOfGenClass));

            code.append("private ");
            code.append(genCodeTypeField(bdColomnsEntity) + " ");
            code.append(genCodeCommonFunction.toCamelCase(bdColomnsEntity.getCode()) + ";\n");

        }
        return code;
    }

    private StringBuilder genCodeTypeField(VBdColomnsEntity entity) {
        StringBuilder code = new StringBuilder();

        if (entity.getTypeColomn().equals(ObjectTypes.getBOOLEAN())) return code.append(" boolean ");
        if (entity.getTypeColomn().equals(ObjectTypes.getDATE())) return code.append(" Date ");
        if (entity.getTypeColomn().equals(ObjectTypes.getSTRING())) return code.append(" String ");
        if (entity.getTypeColomn().equals(ObjectTypes.getNUMBER())) return code.append(" BigDecimal ");
        if (entity.getTypeColomn().equals(ObjectTypes.getREFERENCE()))
            return code.append(genCodeCommonFunction.getFullClassName(entity.getTypeValue(), TypeOfGenClass.ENTITY_CLASS));
        if (entity.getTypeColomn().equals(ObjectTypes.getARRAY())) return code.append(" BigDecimal ");

        return code.append("genCodeTypeField: НЕ предусмотерна обработка ");
    }
}
