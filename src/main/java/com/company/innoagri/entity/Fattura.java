package com.company.innoagri.entity;

import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JmixEntity
@Table(name = "FATTURA", indexes = {
        @Index(name = "IDX_FATTURA_CLIENTE", columnList = "CLIENTE_ID"),
        @Index(name = "IDX_FATTURA_ATTIVITA", columnList = "ATTIVITA_ID")
})
@Entity
public class Fattura {

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)

    @Id
    private Long id;

    @Column(name = "NUMERO_FATTURA")
    private String numeroFattura;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cliente cliente;

    @JoinColumn(name = "ATTIVITA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Attivita attivita;

    @Column(name = "DATA_", nullable = false)
    @NotNull
    private LocalDate data;

    @Column(name = "ANNO_FATTURA", nullable = false)
    @NotNull
    private Integer annoFattura;

    @Column(name = "TOTALE")
    private Double totale;

    public String getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(String numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public Double getTotale() {
        return totale;
    }

    public void setTotale(Double totale) {
        this.totale = totale;
    }

    public Integer getAnnoFattura() {
        return annoFattura;
    }

    public void setAnnoFattura(Integer annoFattura) {
        this.annoFattura = annoFattura;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Attivita getAttivita() {
        return attivita;
    }

    public void setAttivita(Attivita attivita) {
        this.attivita = attivita;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}