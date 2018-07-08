package ru.vood.Plugin.admPlugin.generateCode.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenAnnitationFieldsServiceKT
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenFieldsServiceKT
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.addingImport.AddJavaClass
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.except.ApplicationException
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import java.math.BigDecimal
import java.util.*

@Component
class GenFieldsImplKT : GenFieldsServiceKT {

    @Autowired
    private lateinit var genCodeCommonFunction: GenCodeCommonFunctionKT

    @Autowired
    private lateinit var genAnnitationFieldsService: GenAnnitationFieldsServiceKT

    @Autowired
    private lateinit var addJavaClass: AddJavaClass


    override fun genCode(entity: VBdColomnsEntity, typeOfGenClass: TypeOfGenClass): StringBuilder {
        val code = StringBuilder()
        if (typeOfGenClass == TypeOfGenClass.ENTITY_CLASS) {
            code.append("/*Наименование поля - ${entity.name}*/\n")
            code.append(genAnnitationFieldsService.genCode(entity, typeOfGenClass))
            code.append("lateinit var ")
            code.append(genCodeCommonFunction.genFieldName(entity).toString()).append(" : ")
            code.append(genColumnClass(entity)).append("\n\n")
        }
        return code
    }

    private fun genColumnClass(col: VBdColomnsEntity): String =
            when (col.typeColomn) {
                ObjectTypes.getSTRING() -> " String "
                ObjectTypes.getNUMBER() -> {
                    addJavaClass.getCode(BigDecimal::class.java)
                    " BigDecimal "
                }
                ObjectTypes.getDATE() -> {
                    addJavaClass.getCode(Date::class.java)
                    " Date "
                }
                ObjectTypes.getBOOLEAN() -> {
                    addJavaClass.getCode(Boolean::class.java)
                    " Boolean "
                }
                ObjectTypes.getARRAY() -> {
                    addJavaClass.getCode(BigDecimal::class.java)
                    " BigDecimal "
                }
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
            ObjectTypes.getREFERENCE() -> code.append(genCodeCommonFunction.getFullClassName(entity.typeValue, TypeOfGenClass.ENTITY_CLASS))
            ObjectTypes.getARRAY() -> code.append(" BigDecimal ")
            else -> code.append("genCodeTypeField: НЕ предусмотерна обработка ${entity.typeColomn}")
        }

        return code
    }

}