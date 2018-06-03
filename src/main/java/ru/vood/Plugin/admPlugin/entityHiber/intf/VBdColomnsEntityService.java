package ru.vood.Plugin.admPlugin.entityHiber.intf;

import ru.vood.Plugin.admPlugin.entityHiber.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectEntity;

import java.util.List;

public interface VBdColomnsEntityService extends VBdObjectEntityService {

    List<VBdColomnsEntity> findByParent(VBdObjectEntity parent);
}
