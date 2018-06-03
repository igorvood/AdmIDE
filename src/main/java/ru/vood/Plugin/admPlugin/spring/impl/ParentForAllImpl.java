package ru.vood.Plugin.admPlugin.spring.impl;

import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll;
import ru.vood.Plugin.admPlugin.spring.intf.ParentForAllServise;

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
