package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects
import ru.vood.Plugin.admPlugin.tune.PluginTunes

@Component
class GenCodeCommonFunctionKT {
    @Autowired
    private lateinit var pluginTunes: PluginTunes

    fun getTableName(entity: VBdObjectEntity): StringBuilder {
        return StringBuilder((pluginTunes.prefixTable + entity.code).toUpperCase())
    }

    @JvmOverloads
    fun getClassName(entity: VBdObjectEntity
                     , typeOfGenClassKT: TypeOfGenClassKT = TypeOfGenClassKT.ENTITY_CLASS): StringBuilder {
        return StringBuilder(toCamelCase(getTableName(entity).toString() + "_" + typeOfGenClassKT)!!)
    }

    fun getPackegeName(typeOfGenClassKT: TypeOfGenClassKT): StringBuilder {
        return StringBuilder(pluginTunes.packageIn + "." + typeOfGenClassKT.toString().toLowerCase())
    }

    @JvmOverloads
    fun getFullClassName(entity: VBdObjectEntity
                         , typeOfGenClassKT: TypeOfGenClassKT = TypeOfGenClassKT.ENTITY_CLASS): StringBuilder {
        val code = StringBuilder(getPackegeName(typeOfGenClassKT))
        return code.append(getClassName(entity, typeOfGenClassKT))
    }

    fun getIdField(): StringBuilder = StringBuilder("    @Id\n" +
            "    @GenericGenerator(name = \"seqId\", strategy = \"ru.vood.Plugin.admPlugin.spring.entity.GeneratorId\")\n" +
            "    @GeneratedValue(generator = \"seqId\")\n" +
            "    @Column(name = \"ID\", nullable = false, precision = 0)\n" +
            "    private BigDecimal id;\n")

    @JvmOverloads
    fun isRootEntity(
            entity: VBdObjectEntity,
            typeOfGenClassKT: TypeOfGenClassKT = TypeOfGenClassKT.ENTITY_CLASS
    ): Boolean {
        return if (typeOfGenClassKT == TypeOfGenClassKT.ENTITY_CLASS) {
            entity.parent != null && RootObjects.isRoot(entity.parent)
        } else false
    }

    @JvmOverloads
    fun getExtendsClassName(entity: VBdObjectEntity, typeOfGenClassKT: TypeOfGenClassKT = TypeOfGenClassKT.ENTITY_CLASS): StringBuilder {
        val code = StringBuilder("")
        return if (isRootEntity(entity, typeOfGenClassKT)) {
            code.append(" extends ").append(getFullClassName(entity.parent, typeOfGenClassKT))
        } else code
    }


    fun toCamelCase(s: String?): StringBuilder? {
        if (s == null)
            return null

        val ret = StringBuilder(s.length)

        for (word in s.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase())
                ret.append(word.substring(1).toLowerCase())
            }
        }
        return ret
    }

}