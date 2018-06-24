package ru.vood.Plugin.admPlugin.spring.generateCode.impl;

import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;
import ru.vood.Plugin.admPlugin.spring.generateCode.intf.GenAnnitationFieldsService;

@Component
public class GenAnnitationFieldsImpl implements GenAnnitationFieldsService {
    @Override
    public StringBuilder genCode(VBdTableEntity entity, TypeOfGenClass typeOfGenClass) {
        return null;
    }
}
