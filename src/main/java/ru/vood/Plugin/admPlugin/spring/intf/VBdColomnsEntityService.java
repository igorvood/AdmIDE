package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

import java.util.List;

public interface VBdColomnsEntityService extends VBdObjectEntityService {

    List<VBdColomnsEntity> findByParent(VBdObjectEntity parent);
}
