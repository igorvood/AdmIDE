package ru.vood.Plugin.admPlugin.spring.impl;

import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll;

@Deprecated
public abstract class ParentForAllImpl<S extends ParentForAll> extends CommonFunction {

    /*public <S extends ParentForAll> S save(S s) {
        return (S) s.save();
    }*/
    public abstract S save(S s);

    public abstract void delete(S s);

}
