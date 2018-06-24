package ru.vood.Plugin.admPlugin.spring.generateCode.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;

public interface GenAnyPart {

    StringBuilder genCode(VBdObjectEntity entity, TypeOfGenClass typeOfGenClass);
}
