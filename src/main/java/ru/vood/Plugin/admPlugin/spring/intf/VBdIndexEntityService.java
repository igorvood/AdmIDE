package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

import java.util.List;

public interface VBdIndexEntityService {

    VBdIndexEntity save(VBdIndexEntity entity);

    void delete(VBdIndexEntity entity);

    List<VBdIndexEntity> findByParent(VBdObjectEntity parent);

}
