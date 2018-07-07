package ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.GenCodeCommonFunctionKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.TypeOfGenClassKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenAnnotationClassServiceKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenAnyPartKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenClassBodyServiceKT
import ru.vood.Plugin.admPlugin.spring.generateCode.kotlin.intf.GenImportServiceKT

@Component
class GenClassServiceKT : GenAnyPartKT {


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

    override fun genCode(entity: VBdObjectEntity, typeOfGenClass: TypeOfGenClassKT): StringBuilder {
        val code = StringBuilder()

        code.append(genPackageImpl!!.genCode(entity, typeOfGenClass))

        //code.append(genImportService!!.genCode(entity, typeOfGenClass))

        code.append(genAnnotationClassService!!.genCode(entity, typeOfGenClass))

        code.append("public class " + commonFunction!!.getClassName(entity, typeOfGenClass) + commonFunction!!.getExtendsClassName(entity, typeOfGenClass) + " {\n")

        code.append(classBodyService!!.genCode(entity, typeOfGenClass))

        code.append(" }")

        return code
    }

}