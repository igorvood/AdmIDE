package ru.vood.Plugin.admPlugin.entityHiber.intf;

import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectTypeEntity;

import java.util.List;

public interface VBdObjectTypeEntityService extends ParentForAllServise {

    //-------------------------------individual----------------------------

    List<VBdObjectTypeEntity> findByCodeIn(String... code);

    List<VBdObjectTypeEntity> findByCodeS(String... codeS);

    VBdObjectTypeEntity findByCode(String code);

}
