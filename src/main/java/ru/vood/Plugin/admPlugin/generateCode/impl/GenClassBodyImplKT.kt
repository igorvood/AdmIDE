package ru.vood.Plugin.admPlugin.generateCode.impl

import org.hibernate.annotations.GenericGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenClassBodyServiceKT
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenFieldsServiceKT
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.addingImport.AddAnnotationClass
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.addingImport.AddJavaClass
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.addingImport.ParamOfAnnotation
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService
import java.math.BigDecimal
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Component
class GenClassBodyImplKT : GenClassBodyServiceKT {

    @Autowired
    private lateinit var genCodeCommonFunction: GenCodeCommonFunctionKT

    @Autowired
    private lateinit var colomnsEntityService: VBdColomnsEntityService

    @Autowired
    private lateinit var genFieldsService: GenFieldsServiceKT

    @Autowired
    private lateinit var addAnnotationClass: AddAnnotationClass

    @Autowired
    private lateinit var addJavaClass: AddJavaClass


    private fun genCodeEntiy(entity: VBdTableEntity): StringBuilder {
        val code = StringBuilder()
        if (genCodeCommonFunction.isRootEntity(entity)) {
            code.append(getIdField())
        }

        val colomnsEntities = colomnsEntityService.findByParent(entity)

        for (colomn in colomnsEntities) {
            code.append(genFieldsService.genCode(colomn, TypeOfGenClassKT.ENTITY_CLASS))
        }

        return code
    }

    private fun getIdField(): StringBuilder {
        val res = StringBuilder()
        val param = ParamOfAnnotation()
        res.append("/*Уникальный Идентификатор*/\n")

        res.append(addAnnotationClass.getCode(Id::class.java))

        param.put("name", "\"seqId\"")
        param.put("strategy", "\"ru.vood.Plugin.admPlugin.spring.entity.GeneratorId\"")
        res.append(addAnnotationClass.getCode(GenericGenerator::class.java, param))

        param.clear()
        param.put("generator", "\"seqId\"")
        res.append(addAnnotationClass.getCode(GeneratedValue::class.java, param))

        addJavaClass.getCode(BigDecimal::class.java)
        res.append("lateinit var id :  BigDecimal\n\n")
        return res
    }

    @JvmOverloads
    override fun genCode(entity: VBdTableEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        val code = StringBuilder()
        return if (typeOfGenClass == TypeOfGenClassKT.ENTITY_CLASS) genCodeEntiy(entity) else code
    }


}