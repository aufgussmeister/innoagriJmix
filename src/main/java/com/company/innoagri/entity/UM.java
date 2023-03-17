package com.company.innoagri.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum UM implements EnumClass<String> {

    ETTARI("A"),
    METRI("B"),
    ORE("C"),
    QLI("D"),
    PEZZI("E"),
    PALI("F"),
    LT("G"),
    KG("H"),
    GR("I");

    private String id;

    UM(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static UM fromId(String id) {
        for (UM at : UM.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}