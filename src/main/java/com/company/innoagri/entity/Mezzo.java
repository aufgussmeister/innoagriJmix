package com.company.innoagri.entity;

import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.CaseConversion;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JmixEntity
@Table(name = "MEZZO")
@Entity
public class Mezzo {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @CaseConversion
    @Column(name = "MODELLO")
    private String modello;

    @CaseConversion
    @Column(name = "TARGA")
    private String targa;

    @Column(name = "CAVALLI")
    private Integer cavalli;

    @CaseConversion
    @Column(name = "COSTRUTTORE")
    private String costruttore;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    public String getTenant() { return tenant; }

    public void setTenant(String value) {
        this.tenant = value;
    }

    public String getCostruttore() {
        return costruttore;
    }

    public void setCostruttore(String costruttore) {
        this.costruttore = costruttore;
    }

    public Integer getCavalli() {
        return cavalli;
    }

    public void setCavalli(Integer cavalli) {
        this.cavalli = cavalli;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    @InstanceName
    @DependsOnProperties({"costruttore", "targa"})
    public String getInstanceName() {
        return String.format("%s %s - %s", costruttore,cavalli.toString(), targa);
    }
}