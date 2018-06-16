package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntityTest;

public interface VBdIndexEntityTestService {

    VBdIndexEntityTest save(VBdIndexEntityTest entity);

    void delete(VBdIndexEntityTest entity);

}
