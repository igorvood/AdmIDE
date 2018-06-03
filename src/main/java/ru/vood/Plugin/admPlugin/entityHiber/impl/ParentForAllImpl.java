package ru.vood.Plugin.admPlugin.entityHiber.impl;

import ru.vood.Plugin.admPlugin.entityHiber.ParentForAll;
import ru.vood.Plugin.admPlugin.entityHiber.intf.ParentForAllServise;

public abstract class ParentForAllImpl {

    /*public <S extends ParentForAll> S save(S s) {
        return (S) s.save();
    }*/
    public abstract <S extends ParentForAll> S save(S s);

    public <S extends ParentForAll> void delete(S s) {
        ParentForAllServise service = s.getServise();
        service.delete(s);
    }

}
