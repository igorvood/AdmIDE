package ru.vood.Plugin.admPlugin.spring.generateCode.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;

public interface GenAnyPart {

    StringBuilder genCode(VBdTableEntity entity, TypeOfGenClass typeOfGenClass);
}
