package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;

public interface VBdObjectTypeEntityService extends ParentForAllServise {

    //-------------------------------individual----------------------------

    /*List<VBdObjectTypeEntity> findByCodeIn(String... code);

    List<VBdObjectTypeEntity> findByCodeS(String... codeS);

    VBdObjectTypeEntity findByCode(String code);
    */

    VBdObjectTypeEntity findByCode(String code);
}
