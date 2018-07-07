package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.vood.Plugin.admPlugin.BaseTest
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity

class LimitingNameDBMSTest : BaseTest() {

    private lateinit var limitingNameDBMS: LimitingNameDBMS

    @Before
    fun setVar() {
        limitingNameDBMS = ctx.getBean(LimitingNameDBMS::class.java)
    }

    @Test
    fun getNameObj() {
        var upLimitObjName = "uplimit"
        var objName = "asdfghjklpoiuytrewqzxcvbnmqwoi" + upLimitObjName
        val newObjName = limitingNameDBMS.getNameObj(objName)
        println("objName = $objName,  newObjName = $newObjName")
        Assert.assertNotEquals(objName, newObjName)
        Assert.assertEquals(newObjName.length, 30)
    }

    @Test
    fun getNameObjByObj() {
        var upLimitObjName = "uplimit"
        var objName = "asdfghjklpoiuytrewqzxcvbnmqwo_" + upLimitObjName

        var obj = VBdObjectEntity()
        obj.code = objName

        val newObj = limitingNameDBMS.getNameObj(obj)
        println("objName = $objName,  newObjName = ${newObj.code},  id=${newObj.id}")

        Assert.assertNotEquals(newObj.code, objName)
        Assert.assertEquals(newObj.code.length, 30)
        Assert.assertNotNull(newObj.id)
    }
    //   public VBdObjectEntity getNameObj(VBdObjectEntity entity) {

}