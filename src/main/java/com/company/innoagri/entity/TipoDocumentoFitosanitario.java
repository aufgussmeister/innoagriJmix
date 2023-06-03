package com.company.innoagri.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum TipoDocumentoFitosanitario implements EnumClass<String> {

    DDT_FORNITORE("DDT FORNITORE"),
    DDT_CLIENTE("DDT CLIENTE"),
    RETTIFICA_INVENTARIALE("RETTIFICA INVENTARIALE"),
    TRATTAMENTO("TRATTAMENTO"),
    CHIUSURA_MAGAZZINO("CHIUSURA MAGAZZINO"),
    APERTURA_MAGAZZINO("APERTURA MAGAZZINO");

    private String id;

    TipoDocumentoFitosanitario(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TipoDocumentoFitosanitario fromId(String id) {
        for (TipoDocumentoFitosanitario at : TipoDocumentoFitosanitario.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}