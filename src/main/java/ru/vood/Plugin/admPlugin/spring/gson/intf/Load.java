package ru.vood.Plugin.admPlugin.spring.gson.intf;

import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll;

import java.io.File;
import java.util.ArrayList;

public interface Load<E extends ParentForAll> {

    ArrayList<E> loadFrom(File file);

}
