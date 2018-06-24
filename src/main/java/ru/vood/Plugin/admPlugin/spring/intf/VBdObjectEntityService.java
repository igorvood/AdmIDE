package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.except.CoreExeption;

import java.math.BigDecimal;
import java.util.List;

public interface VBdObjectEntityService /*extends ParentForAllServise*/ {

    VBdObjectEntity findOne(BigDecimal id);

    VBdObjectEntity save(VBdObjectEntity entity);

    void delete(VBdObjectEntity entity);

    //-----------------------individual--------------------------------

    List<VBdObjectEntity> findByParent(VBdObjectEntity parent);

    List<VBdObjectEntity> findByParentAndTypeObject(VBdObjectEntity parent, VBdObjectTypeEntity objectTypeEntity);
    //<S extends VBdObjectEntity> List<S> findByCode(String code);

    List<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS);

    VBdObjectEntity findByCode(String code) throws CoreExeption;

    VBdObjectEntity findByCodeAndParenCode(String code, String parentCode) throws CoreExeption;
    //List<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent(String codeS, String typeObjectCode, VBdObjectEntity parent);

    //ArrayList<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent_TEST(String code, String typeObjectCode, VBdObjectEntity parent);


}
