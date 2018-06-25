package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
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

            code.append("private ")
            code.append(genCodeTypeField(bdColomnsEntity)).append(" ")
            code.append(genCodeCommonFunction.toCamelCase(bdColomnsEntity.code)!!.toString() + ";\n")

        }
        return code
    }

    private fun genCodeTypeField(entity: VBdColomnsEntity): StringBuilder {
        val code = StringBuilder()
        when (entity.typeColomn) {
            ObjectTypes.getBOOLEAN() -> code.append(" boolean ")
            ObjectTypes.getDATE() -> code.append(" Date ")
            ObjectTypes.getSTRING() -> code.append(" String ")
            ObjectTypes.getNUMBER() -> code.append(" BigDecimal ")
            ObjectTypes.getREFERENCE() -> code.append(genCodeCommonFunction.getFullClassName(entity.typeValue, TypeOfGenClassKT.ENTITY_CLASS))
            ObjectTypes.getARRAY() -> code.append(" BigDecimal ")
        }
        return code.append("genCodeTypeField: НЕ предусмотерна обработка ")
    }

}