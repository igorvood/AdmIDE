package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;

public interface VBdIndexEntityService {

    VBdIndexEntity save(VBdIndexEntity entity);

    void delete(VBdIndexEntity entity);

}
