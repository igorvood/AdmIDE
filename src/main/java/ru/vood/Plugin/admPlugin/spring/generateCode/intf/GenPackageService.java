package ru.vood.Plugin.admPlugin.spring.generateCode.intf;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.GenCodeCommonFunction;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;

public abstract class GenPackageService implements GenAnyPart {

    @Autowired
    private GenCodeCommonFunction genCodeCommonFunction;

    @Override
    public StringBuilder genCode(VBdTableEntity entity, TypeOfGenClass typeOfGenClass) {
        return new StringBuilder("package " + genCodeCommonFunction.getPackegeName(typeOfGenClass) + ";\n\n");
    }
}