package ru.vood.Plugin.admPlugin.gson.intf;

import com.google.gson.Gson;

import java.io.Serializable;


public interface GSonRepository extends Serializable {

    public Gson getGson();
}
