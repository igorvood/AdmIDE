package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.except.CoreExeption;

import java.util.List;

public interface VBdIndexEntityService {

    VBdIndexEntity findByCode(String code) throws CoreExeption;

    VBdIndexEntity save(VBdIndexEntity entity);

    void delete(VBdIndexEntity entity);

    List<VBdIndexEntity> findByParent(VBdObjectEntity parent);

}
