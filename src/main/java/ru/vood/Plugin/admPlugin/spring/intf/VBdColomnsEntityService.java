package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

import java.util.List;

public interface VBdColomnsEntityService /*extends VBdObjectEntityService*/ {

    VBdColomnsEntity save(VBdColomnsEntity entity);

    void delete(VBdColomnsEntity entity);

    List<VBdColomnsEntity> findByParent(VBdObjectEntity parent);

    //List<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS);

}
