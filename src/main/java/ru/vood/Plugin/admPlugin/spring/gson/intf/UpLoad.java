package ru.vood.Plugin.admPlugin.spring.gson.intf;

import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll;

import java.io.File;
import java.util.ArrayList;

public interface UpLoad<E extends ParentForAll> {

    boolean upLoadTo(File file, ArrayList<E> arrayList);

}
