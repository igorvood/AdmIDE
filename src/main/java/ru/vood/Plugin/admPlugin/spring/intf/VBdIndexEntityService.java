package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

import java.util.List;

public interface VBdIndexEntityService /*extends  ParentForAllServise/*<S extends VBdIndexEntity> /*extends VBdObjectEntityService*/ {

    VBdIndexEntity save(VBdIndexEntity entity);

    void delete(VBdIndexEntity entity);

    List<VBdIndexEntity> findByParent(VBdObjectEntity parent);

    List<VBdIndexEntity> findByTypeObjectCodeIn(String... codeS);
}
