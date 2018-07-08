package ru.vood.Plugin.admPlugin.generateCode.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenAnnotationClassServiceKT
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenAnyPartKT
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenClassBodyServiceKT
import ru.vood.Plugin.admPlugin.generateCode.impl.intf.GenImportServiceKT
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity

@Component
class GenClassServiceKT : GenAnyPartKT<VBdTableEntity> {

    @Autowired
    private lateinit var commonFunction: GenCodeCommonFunctionKT

    @Autowired
    private lateinit var genPackageImpl: GenPackageImplKT

    @Autowired
    private lateinit var genImportService: GenImportServiceKT

    @Autowired
    private lateinit var classBodyService: GenClassBodyServiceKT

    @Autowired
    private lateinit var genAnnotationClassService: GenAnnotationClassServiceKT

    override fun genCode(entity: VBdTableEntity, typeOfGenClass: TypeOfGenClass): StringBuilder {
        val code = StringBuilder()

        code.append(genPackageImpl!!.genCode(entity, typeOfGenClass))

        val annotationClass = genAnnotationClassService!!.genCode(entity, typeOfGenClass)

        val clazz = "/*Наименование класса - ${entity.name}*/\n" +
                "open class " + commonFunction!!.getClassName(entity, typeOfGenClass) + commonFunction!!.getExtendsClassName(entity, typeOfGenClass)

        val body = classBodyService!!.genCode(entity, typeOfGenClass)

        val import = genImportService!!.genCode(entity, typeOfGenClass)

        code.append(import)
        code.append(annotationClass)
        code.append(clazz)
        code.append("{\n").append(body).append("}")

        return code
    }

}