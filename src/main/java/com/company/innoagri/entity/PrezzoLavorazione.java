package com.company.innoagri.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.time.LocalDate;

@JmixEntity
@Table(name = "PREZZO_LAVORAZIONE", indexes = {
        @Index(name = "IDX_PREZZO_LAVORAZIONE_LAVORAZIONE", columnList = "LAVORAZIONE_ID")
})
@Entity
public class PrezzoLavorazione {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    @InstanceName
    @Column(name = "DATA_INIZIO")
    private LocalDate dataInizio;

    @Column(name = "IN_VIGORE")
    private Boolean inVigore;

    @Column(name = "DATA_FINE")
    private LocalDate dataFine;

    @Column(name = "UNITA_DI_MISURA")
    private String unitaDiMisura;

    @Column(name = "PREZZO")
    private Double prezzo;

    @Column(name = "PREZZO_ORA")
    private Double prezzoOra;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "LAVORAZIONE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lavorazione lavorazione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lavorazione getLavorazione() {
        return lavorazione;
    }

    public void setLavorazione(Lavorazione lavorazione) {
        this.lavorazione = lavorazione;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public Boolean getInVigore() {
        return inVigore;
    }

    public void setInVigore(Boolean inVigore) {
        this.inVigore = inVigore;
    }

    public Double getPrezzoOra() {
        return prezzoOra;
    }

    public void setPrezzoOra(Double prezzoOra) {
        this.prezzoOra = prezzoOra;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public UM getUnitaDiMisura() {
        return unitaDiMisura == null ? null : UM.fromId(unitaDiMisura);
    }

    public void setUnitaDiMisura(UM unitaDiMisura) {
        this.unitaDiMisura = unitaDiMisura == null ? null : unitaDiMisura.getId();
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

}

