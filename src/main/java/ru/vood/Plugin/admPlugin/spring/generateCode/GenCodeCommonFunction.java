package ru.vood.Plugin.admPlugin.spring.generateCode;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Component
@Deprecated
public class GenCodeCommonFunction {

    @Autowired
    private PluginTunes pluginTunes;

    public StringBuilder getTableName(@NotNull VBdObjectEntity entity) {
        return new StringBuilder((pluginTunes.getPrefixTable() + entity.getCode()).toUpperCase());
    }

    public StringBuilder getClassName(@NotNull VBdObjectEntity entity, @NotNull TypeOfGenClass typeOfGenClass) {
        return new StringBuilder(toCamelCase(getTableName(entity).toString() + "_" + typeOfGenClass));
    }

    public StringBuilder getPackegeName(TypeOfGenClass typeOfGenClass) {
        return new StringBuilder(pluginTunes.getPackageIn() + "." + typeOfGenClass.toString().toLowerCase());
    }

    public StringBuilder getFullClassName(@NotNull VBdObjectEntity entity, @NotNull TypeOfGenClass typeOfGenClass) {
        StringBuilder code = new StringBuilder(getPackegeName(typeOfGenClass));
        return code.append(getClassName(entity, typeOfGenClass));
    }

    public StringBuilder getIdField() {
        return new StringBuilder("    @Id\n" +
                "    @GenericGenerator(name = \"seqId\", strategy = \"ru.vood.Plugin.admPlugin.spring.entity.GeneratorId\")\n" +
                "    @GeneratedValue(generator = \"seqId\")\n" +
                "    @Column(name = \"ID\", nullable = false, precision = 0)\n" +
                "    private BigDecimal id;\n");
    }

    public boolean isRootEntity(@NotNull VBdObjectEntity entity, @NotNull TypeOfGenClass typeOfGenClass) {
        if (typeOfGenClass.equals(TypeOfGenClass.ENTITY_CLASS)) {
            if (entity.getParent() != null && RootObjects.isRoot(entity.getParent())) return true;
            return false;
        }
        return false;
    }

    public StringBuilder getExtendsClassName(@NotNull VBdObjectEntity entity, @NotNull TypeOfGenClass typeOfGenClass) {
        StringBuilder code = new StringBuilder("");
        if (isRootEntity(entity, typeOfGenClass)) {
            return code.append(" extends ").append(getFullClassName(entity.getParent(), typeOfGenClass));
        }
        return code;
    }

    public StringBuilder toCamelCase(String s) {
        if (s == null)
            return null;

        StringBuilder ret = new StringBuilder(s.length());

        for (String word : s.split("_")) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase());
                ret.append(word.substring(1).toLowerCase());
            }
        }
        return ret;
    }
}
