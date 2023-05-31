package com.company.innoagri.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum TipologiaAppezzamento implements EnumClass<String> {

    TUTTI("TUTTI"),
    IN_PRODUZIONE("IN PRODUZIONE"),
    NON_PRODUTTIVI("NON PRODUTTIVI");

    private String id;

    TipologiaAppezzamento(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TipologiaAppezzamento fromId(String id) {
        for (TipologiaAppezzamento at : TipologiaAppezzamento.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}