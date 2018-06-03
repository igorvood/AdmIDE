package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

import java.util.ArrayList;
import java.util.List;

public interface VBdObjectEntityService extends ParentForAllServise {

    //-----------------------individual--------------------------------
    <S extends VBdObjectEntity> List<S> findByCode(String code);

    List<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS);

    List<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent(String codeS, String typeObjectCode, VBdObjectEntity parent);

    ArrayList<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent_TEST(String code, String typeObjectCode, VBdObjectEntity parent);
}
