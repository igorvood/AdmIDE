package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.except.ApplicationException
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.GenCodeCommonFunctionKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenAnnitationFieldsServiceKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenFieldsServiceKT
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes

@Component
class GenFieldsImplKT : GenFieldsServiceKT {

    @Autowired
    private lateinit var genCodeCommonFunction: GenCodeCommonFunctionKT

    @Autowired
    private lateinit var genAnnitationFieldsService: GenAnnitationFieldsServiceKT


    override fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        val code = StringBuilder()
        if (typeOfGenClass == TypeOfGenClassKT.ENTITY_CLASS) {
            val bdColomnsEntity = entity as VBdColomnsEntity
            code.append(genAnnitationFieldsService.genCode(entity, typeOfGenClass))
            code.append("val ")
            code.append(genCodeCommonFunction.genFieldName(bdColomnsEntity).toString()).append(" : ")
            code.append(genColumnClass(bdColomnsEntity)).append("\n")
        }
        return code
    }

    private fun genColumnClass(col: VBdColomnsEntity): String =
            when (col.typeColomn) {
                ObjectTypes.getSTRING() -> " String "
                ObjectTypes.getNUMBER() -> " BigDecimal "
                ObjectTypes.getDATE() -> " Date "
                ObjectTypes.getBOOLEAN() -> " Boolean "
                ObjectTypes.getARRAY() -> " BigDecimal "
                ObjectTypes.getREFERENCE() -> {
                    val tVal = col.typeValue as VBdTableEntity
                    " " + genCodeCommonFunction.getClassName(tVal.toType).toString() + " "
                }
                else -> throw ApplicationException("Невозможно преобразовать тип колонки ${col.typeValue.typeObject.code} ")
            }


    @Deprecated("dfasd")
    private fun genCodeTypeField(entity: VBdColomnsEntity): StringBuilder {
        val code = StringBuilder()
        when (entity.typeColomn) {
            ObjectTypes.getBOOLEAN() -> code.append(" boolean ")
            ObjectTypes.getDATE() -> code.append(" Date ")
            ObjectTypes.getSTRING() -> code.append(" String ")
            ObjectTypes.getNUMBER() -> code.append(" BigDecimal ")
            ObjectTypes.getREFERENCE() -> code.append(genCodeCommonFunction.getFullClassName(entity.typeValue, TypeOfGenClassKT.ENTITY_CLASS))
            ObjectTypes.getARRAY() -> code.append(" BigDecimal ")
            else -> code.append("genCodeTypeField: НЕ предусмотерна обработка ${entity.typeColomn}")
        }

        return code
    }

}