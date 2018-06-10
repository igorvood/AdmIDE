package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.impl;

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
    }

    public boolean allowAdd(String name) {
        return !exeptNames.contains(name);
    }

}
