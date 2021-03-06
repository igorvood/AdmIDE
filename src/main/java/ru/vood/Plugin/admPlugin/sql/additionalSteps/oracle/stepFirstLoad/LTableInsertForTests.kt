package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepFirstLoad

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService
import ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects
import ru.vood.Plugin.admPlugin.spring.referenceBook.Tables
import ru.vood.Plugin.admPlugin.sql.QueryTableNew

@Service
class LTableInsertForTests {

    @Autowired
    private lateinit var bdTableEntityService: VBdTableEntityService

    @Autowired
    private lateinit var objectEntityService: VBdObjectEntityService

    @Autowired
    private lateinit var colomnsEntityService: VBdColomnsEntityService

    fun additionOne(): QueryTableNew {

        val bdObject_str_table = VBdTableEntity()
        bdObject_str_table.code = "STR_160"
        bdObject_str_table.name = "Стока(160)"
        bdObject_str_table.parent = RootObjects.getSTRING()
        bdObject_str_table.typeObject = ObjectTypes.getSTRING()
        bdObject_str_table.length = 160L
        bdObject_str_table.javaClass = VBdTableEntity::class.java.toString()

        val bdObject_str_table_new = bdTableEntityService.save(bdObject_str_table)


        val bdObject_num_table = VBdTableEntity()
        bdObject_num_table.code = "NUM_17_2"
        bdObject_num_table.name = "Число(17,2)"
        bdObject_num_table.parent = RootObjects.getNUMBER()
        bdObject_num_table.typeObject = ObjectTypes.getNUMBER()
        bdObject_num_table.length = 17L
        bdObject_num_table.precision = 2L
        bdObject_num_table.javaClass = VBdTableEntity::class.java.toString()

        val bdObject_num_table_new = bdTableEntityService.save(bdObject_num_table)


        val bdObject_table = VBdTableEntity()
        bdObject_table.code = "CLIENT"
        bdObject_table.name = "Клиенты"
        bdObject_table.parent = RootObjects.getTABLE()
        bdObject_table.typeObject = ObjectTypes.getTABLE()
        bdObject_table.javaClass = VBdTableEntity::class.java.toString()

        val bdObject_table_new = bdTableEntityService.save(bdObject_table)

        var bdObject_table_aderss = VBdTableEntity()
        bdObject_table_aderss.code = "address"
        bdObject_table_aderss.name = "Адреса"
        bdObject_table_aderss.parent = RootObjects.getTABLE()
        bdObject_table_aderss.typeObject = ObjectTypes.getTABLE()
        bdObject_table_aderss.javaClass = VBdTableEntity::class.java.toString()

        bdObject_table_aderss = bdTableEntityService.save(bdObject_table_aderss)

        var colomnsEntity = VBdColomnsEntity()
        colomnsEntity.code = "CITY"
        colomnsEntity.name = "Город"
        colomnsEntity.typeColomn = ObjectTypes.getSTRING()
        colomnsEntity.typeValue = bdObject_str_table_new
        colomnsEntity.parent = bdObject_table_aderss
        colomnsEntity.typeObject = ObjectTypes.getCOLOMN()
        colomnsEntity.javaClass = VBdColomnsEntity::class.java.toString()
        colomnsEntity = colomnsEntityService.save(colomnsEntity)

        colomnsEntity = VBdColomnsEntity()
        colomnsEntity.code = "STREET"
        colomnsEntity.name = "Улица"
        colomnsEntity.typeColomn = ObjectTypes.getSTRING()
        colomnsEntity.typeValue = bdObject_str_table_new
        colomnsEntity.parent = bdObject_table_aderss
        colomnsEntity.typeObject = ObjectTypes.getCOLOMN()
        colomnsEntity.javaClass = VBdColomnsEntity::class.java.toString()
        colomnsEntity = colomnsEntityService.save(colomnsEntity)

        var table = VBdTableEntity()
        val arrCode = bdObject_table_aderss.code + "_ARR"
        table.code = arrCode
        table.name = "Массив <" + bdObject_table_aderss.name + ">"
        table.typeObject = ObjectTypes.getARRAY()
        table.javaClass = VBdTableEntity::class.java.toString()
        table.parent = RootObjects.getARRAY()
        table.toType = bdObject_table_aderss

        table = bdTableEntityService.save(table)

        var bdObject_table_type_adress = VBdTableEntity()
        bdObject_table_type_adress.code = "TYPE_ADRESS"
        bdObject_table_type_adress.name = "Типы адресов"
        bdObject_table_type_adress.parent = objectEntityService.findByCode("TABLE")
        bdObject_table_type_adress.typeObject = ObjectTypes.getTABLE()
        bdObject_table_type_adress.javaClass = VBdTableEntity::class.java.toString()

        bdObject_table_type_adress = bdTableEntityService.save(bdObject_table_type_adress)


        table = VBdTableEntity()
        val refTypeAdr = bdObject_table_type_adress.code + "_REF"
        table.code = refTypeAdr
        table.name = "Ссылка <" + bdObject_table_type_adress.name + ">"
        table.typeObject = ObjectTypes.getREFERENCE()
        table.javaClass = VBdTableEntity::class.java.toString()
        table.parent = objectEntityService.findByCode("REFERENCE")
        table.toType = bdObject_table_type_adress

        table = bdTableEntityService.save(table)


        colomnsEntity = VBdColomnsEntity()
        colomnsEntity.code = "NAME"
        colomnsEntity.name = "ФИО"
        colomnsEntity.typeColomn = ObjectTypes.getSTRING()
        colomnsEntity.typeValue = bdObject_str_table_new
        colomnsEntity.parent = bdObject_table_new
        colomnsEntity.typeObject = ObjectTypes.getCOLOMN()
        colomnsEntity.javaClass = VBdColomnsEntity::class.java.toString()
        colomnsEntity = colomnsEntityService.save(colomnsEntity)

        colomnsEntity = VBdColomnsEntity()
        colomnsEntity.code = "DATE_BIRTH"
        colomnsEntity.name = "Дата рождения"
        colomnsEntity.typeColomn = ObjectTypes.getDATE()
        colomnsEntity.typeValue = Tables.getAny("DATE")
        colomnsEntity.parent = bdObject_table_new
        colomnsEntity.typeObject = ObjectTypes.getCOLOMN()
        colomnsEntity.javaClass = VBdColomnsEntity::class.java.toString()
        colomnsEntity = colomnsEntityService.save(colomnsEntity)

        if (true) {
            colomnsEntity = VBdColomnsEntity()
            colomnsEntity.code = "ADRESESS"
            colomnsEntity.name = "Адреса"
            colomnsEntity.typeColomn = ObjectTypes.getARRAY()
            colomnsEntity.typeValue = Tables.getAny(arrCode)
            colomnsEntity.parent = bdObject_table_new
            colomnsEntity.typeObject = ObjectTypes.getCOLOMN()
            colomnsEntity.javaClass = VBdColomnsEntity::class.java.toString()
            colomnsEntity = colomnsEntityService.save(colomnsEntity)


            colomnsEntity = VBdColomnsEntity()
            colomnsEntity.code = "ADRESES_TYPE"
            colomnsEntity.name = "Тип адреса"
            colomnsEntity.typeColomn = ObjectTypes.getREFERENCE()
            colomnsEntity.typeValue = Tables.getAny(refTypeAdr)
            colomnsEntity.parent = bdObject_table_aderss
            colomnsEntity.typeObject = ObjectTypes.getCOLOMN()
            colomnsEntity.javaClass = VBdColomnsEntity::class.java.toString()
            colomnsEntity = colomnsEntityService.save(colomnsEntity)

            var bdObject_tableOrg = VBdTableEntity()
            bdObject_tableOrg.code = "CLIENT_ORG"
            bdObject_tableOrg.name = "Юридические лица"
            bdObject_tableOrg.parent = bdObject_table
            bdObject_tableOrg.typeObject = ObjectTypes.getTABLE()
            bdObject_tableOrg.javaClass = VBdTableEntity::class.java.toString()
            bdObject_tableOrg = bdTableEntityService.save(bdObject_tableOrg)

            colomnsEntity = VBdColomnsEntity()
            colomnsEntity.code = "KPP"
            colomnsEntity.name = "КПП"
            colomnsEntity.typeColomn = ObjectTypes.getSTRING()
            colomnsEntity.typeValue = Tables.getAny(bdObject_str_table_new.code)
            colomnsEntity.parent = bdObject_tableOrg
            colomnsEntity.typeObject = ObjectTypes.getCOLOMN()
            colomnsEntity.javaClass = VBdColomnsEntity::class.java.toString()
            colomnsEntity = colomnsEntityService.save(colomnsEntity)
        }

        return QueryTableNew()
    }

}