package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.except.ApplicationException
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenAnnitationFieldsServiceKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport.AddAnnotationClass
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.addingImport.ParamOfAnnotation
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import javax.persistence.*

@Component
class GenAnnitationFieldsImplKT : GenAnnitationFieldsServiceKT {

    @Autowired
    private lateinit var addAnnotationClass: AddAnnotationClass

    override fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        val col = entity as VBdColomnsEntity
        when (col.typeColomn) {
            ObjectTypes.getSTRING() -> genSimple(col)
            ObjectTypes.getNUMBER() -> genSimple(col)
            ObjectTypes.getDATE() -> genDate(col)
            ObjectTypes.getBOOLEAN() -> genSimple(col)
            ObjectTypes.getARRAY() -> genSimple(col)
            ObjectTypes.getREFERENCE() -> genRef(col)
            else -> throw ApplicationException("Невозможно преобразовать тип колонки ${col.typeValue.typeObject.code} ")
        }

        return StringBuilder("")
    }

    private fun genSimple(col: VBdColomnsEntity): StringBuilder {
        var param = ParamOfAnnotation()
        param.put("name ", "\"" + col.name + "\"")
        val nullable = if (col.notNull) "true" else "false"
        param.put("nullable ", nullable)
        return StringBuilder(addAnnotationClass.getCode(Column::class.java, param))
    }

    private fun genDate(col: VBdColomnsEntity): StringBuilder {
        val ret = genSimple(col)
        //для того что бы в импорт добавить
        addAnnotationClass.getCode(TemporalType::class.java)

        val param = ParamOfAnnotation()
        param.put("TemporalType.DATE", "")
        ret.append(addAnnotationClass.getCode(Temporal::class.java, param))

        return ret
    }

    private fun genRef(col: VBdColomnsEntity): StringBuilder {
        val ret = StringBuilder()
        val paramManyToOne = ParamOfAnnotation()
        addAnnotationClass.getCode(FetchType::class.java)

        paramManyToOne.put("fetch", "FetchType.LAZY")
        ret.append(addAnnotationClass.getCode(ManyToOne::class.java, paramManyToOne))

        val paramJoinColumn = ParamOfAnnotation()
        paramJoinColumn.put("name", "\"${col.code}\"")
        paramJoinColumn.put("referencedColumnName", "\"ID\"")

        ret.append(addAnnotationClass.getCode(JoinColumn::class.java, paramJoinColumn))

        return ret
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PARENT", referencedColumnName = "ID")


}