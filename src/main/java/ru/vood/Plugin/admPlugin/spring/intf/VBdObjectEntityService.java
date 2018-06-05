package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

import java.util.List;

public interface VBdObjectEntityService /*extends ParentForAllServise*/ {

    VBdObjectEntity save(VBdObjectEntity entity);

    void delete(VBdObjectEntity entity);

    //-----------------------individual--------------------------------

    List<VBdObjectEntity> findByParent(VBdObjectEntity parent);
    //<S extends VBdObjectEntity> List<S> findByCode(String code);

    List<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS);

    //List<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent(String codeS, String typeObjectCode, VBdObjectEntity parent);

    //ArrayList<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent_TEST(String code, String typeObjectCode, VBdObjectEntity parent);


}
