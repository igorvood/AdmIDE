package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.except.CoreExeption;

import java.util.List;

public interface VBdColomnsEntityService /*extends  ParentForAllServise/*extends VBdObjectEntityService*/ {

    VBdColomnsEntity save(VBdColomnsEntity entity);

    void delete(VBdColomnsEntity entity);

    List<VBdColomnsEntity> findByParent(VBdTableEntity parent);

    VBdColomnsEntity findColomn(VBdTableEntity parent, String code) throws CoreExeption;
    //List<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS);

}
