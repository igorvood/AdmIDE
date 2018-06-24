package ru.vood.Plugin.admPlugin.spring.generateCode.impl;

import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;
import ru.vood.Plugin.admPlugin.spring.generateCode.intf.GenImportService;

@Component
public class GenImportImpl implements GenImportService {

    @Override
    public StringBuilder genCode(VBdObjectEntity entity, TypeOfGenClass typeOfGenClass) {
        StringBuilder code = new StringBuilder();
        return code;

    }
}
