package com.company.innoagri.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Priorita implements EnumClass<String> {

    ALTA("A"),
    MEDIA("B"),
    BASSA("C");

    private String id;

    Priorita(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Priorita fromId(String id) {
        for (Priorita at : Priorita.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}