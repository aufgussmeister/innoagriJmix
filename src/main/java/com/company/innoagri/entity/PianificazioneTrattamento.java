package com.company.innoagri.entity;

import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "PIANIFICAZIONE_TRATTAMENTO", indexes = {
        @Index(name = "IDX_PIANIFICAZIONE_TRATTAMENTO_CAMPAGNA_TRATTAMENTO", columnList = "CAMPAGNA_TRATTAMENTO_ID")
})
@Entity
public class PianificazioneTrattamento {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InstanceName
    @Column(name = "DATA_", nullable = false)
    @NotNull
    private LocalDate data;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @Composition
    @OneToMany(mappedBy = "pianificazioneTrattamento")
    private List<ProdottoPianificato> prodottiPianificati;

    @JoinColumn(name = "CAMPAGNA_TRATTAMENTO_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CampagnaTrattamenti campagnaTrattamento;

    @JmixGeneratedValue
    @Column(name = "UUID")
    private UUID uuid;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    public CampagnaTrattamenti getCampagnaTrattamento() {
        return campagnaTrattamento;
    }

    public void setCampagnaTrattamento(CampagnaTrattamenti campagnaTrattamento) {
        this.campagnaTrattamento = campagnaTrattamento;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public List<ProdottoPianificato> getProdottiPianificati() {
        return prodottiPianificati;
    }

    public void setProdottiPianificati(List<ProdottoPianificato> prodottiPianificati) {
        this.prodottiPianificati = prodottiPianificati;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}