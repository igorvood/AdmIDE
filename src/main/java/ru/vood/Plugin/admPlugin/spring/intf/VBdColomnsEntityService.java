package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;

import java.util.List;

public interface VBdColomnsEntityService /*extends  ParentForAllServise/*extends VBdObjectEntityService*/ {

    VBdColomnsEntity save(VBdColomnsEntity entity);

    void delete(VBdColomnsEntity entity);

    List<VBdColomnsEntity> findByParent(VBdTableEntity parent);

    VBdColomnsEntity findColomn(VBdTableEntity parent, String code);
    //List<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS);

}
