package com.company.innoagri.entity;

import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.CaseConversion;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;

@JmixEntity
@Table(name = "LAVORAZIONE")
@Entity
public class Lavorazione {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    @InstanceName
    @CaseConversion
    @Column(name = "NOME")
    private String nome;

    @Column(name = "ATTIVO")
    private Boolean attivo;

    @Composition
    @OneToMany(mappedBy = "lavorazione")
    private List<PrezzoLavorazione> prezzi;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    public String getTenant() { return tenant; }

    public void setTenant(String tenant) { this.tenant = tenant; }

    public List<PrezzoLavorazione> getPrezzi() {
        return prezzi;
    }

    public void setPrezzi(List<PrezzoLavorazione> prezzi) {
        this.prezzi = prezzi;
    }



    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}