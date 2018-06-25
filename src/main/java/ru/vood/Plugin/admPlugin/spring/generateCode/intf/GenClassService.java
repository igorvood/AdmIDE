package ru.vood.Plugin.admPlugin.spring.generateCode.intf;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.generateCode.GenCodeCommonFunction;
import ru.vood.Plugin.admPlugin.spring.generateCode.TypeOfGenClass;

@Deprecated
public abstract class GenClassService implements GenAnyPart {

    @Autowired
    private GenCodeCommonFunction commonFunction;

    @Autowired
    private GenPackageService genPackageService;

    @Autowired
    private GenImportService genImportService;

    @Autowired
    private GenClassBodyService classBodyService;

    @Autowired
    private GenAnnotationClassService genAnnotationClassService;

    @Override
    public StringBuilder genCode(VBdObjectEntity entity, TypeOfGenClass typeOfGenClass) {
        StringBuilder code = new StringBuilder();

        code.append(genPackageService.genCode(entity, typeOfGenClass));

        code.append(genImportService.genCode(entity, typeOfGenClass));

        code.append(genAnnotationClassService.genCode(entity, typeOfGenClass));

        code.append("public class " + commonFunction.getClassName(entity, typeOfGenClass) + commonFunction.getExtendsClassName(entity, typeOfGenClass) + " {\n");
        code.append(classBodyService.genCode(entity, typeOfGenClass));
        code.append(" }");

        return code;
    }
}
