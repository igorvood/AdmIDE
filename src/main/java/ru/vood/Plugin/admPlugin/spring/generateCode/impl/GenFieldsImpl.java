package ru.vood.Plugin.admPlugin.spring.generateCode.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;
import ru.vood.Plugin.admPlugin.spring.generateCode.intf.GenAnnitationFieldsService;
import ru.vood.Plugin.admPlugin.spring.generateCode.intf.GenFieldsService;

@Component
public class GenFieldsImpl implements GenFieldsService {

    @Autowired
    private GenAnnitationFieldsService genAnnitationFieldsService;

    @Override
    public StringBuilder genCode(VBdTableEntity entity, TypeOfGenClass typeOfGenClass) {
        StringBuilder code = new StringBuilder();
        code.append(genAnnitationFieldsService.genCode(entity, typeOfGenClass));


        return null;
    }
}
