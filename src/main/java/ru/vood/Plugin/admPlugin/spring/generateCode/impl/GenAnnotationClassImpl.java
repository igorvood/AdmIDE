package ru.vood.Plugin.admPlugin.spring.generateCode.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.GenCodeCommonFunction;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;
import ru.vood.Plugin.admPlugin.spring.generateCode.intf.GenAnnotationClassService;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Component
@Deprecated
public class GenAnnotationClassImpl implements GenAnnotationClassService {

    @Autowired
    private GenCodeCommonFunction genCodeCommonFunction;

    @Autowired
    private PluginTunes pluginTunes;

    @Override
    public StringBuilder genCode(VBdObjectEntity entity, TypeOfGenClass typeOfGenClass) {
        StringBuilder code = new StringBuilder("");

        if (typeOfGenClass.equals(TypeOfGenClass.ENTITY_CLASS)) code.append(genCodeEntity((VBdTableEntity) entity));
        return code;
    }

    private StringBuilder genCodeEntity(VBdTableEntity entity) {
        StringBuilder code = new StringBuilder();
        code.append("@Entity\n");
        code.append("@Table(name = \"" + genCodeCommonFunction.getTableName(entity) + "\" , schema = \"" + pluginTunes.getOwner() + "\")\n");
        if (genCodeCommonFunction.isRootEntity(entity, TypeOfGenClass.ENTITY_CLASS)) {
            code.append("@Inheritance(strategy = InheritanceType.JOINED)\n");
        }
        return code;
    }
}
