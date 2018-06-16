package ru.vood.Plugin.admPlugin.sql;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Component
public class ExeptObjectName {
    private static HashSet<String> exeptNames;

    @PostConstruct
    private void setListExeptNames() {
        exeptNames = new HashSet<>();
        exeptNames.add("OBJECT");
        exeptNames.add("DATE");
        exeptNames.add("REFERENCE");
        exeptNames.add("ARRAY");
        exeptNames.add("STRING");
        exeptNames.add("NUMBER");
        exeptNames.add("TABLE");
        exeptNames.add("INDEX");
    }

    public boolean allowAdd(String name) {
        return !exeptNames.contains(name);
    }

}
