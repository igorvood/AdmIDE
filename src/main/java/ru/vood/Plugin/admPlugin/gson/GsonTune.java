package ru.vood.Plugin.admPlugin.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GsonTune {

    private static Gson gson;

    private GsonTune() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting()
                .serializeNulls()
                .setDateFormat("yyyy.mm.dd ")
                .setVersion(1);
        gson = gsonBuilder.create();
    }

    public static Gson getGson() {
        if (gson == null) {
            new GsonTune();
        }
        return gson;
    }
}
