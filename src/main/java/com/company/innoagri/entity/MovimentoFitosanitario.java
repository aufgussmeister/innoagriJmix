package com.company.innoagri.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@JmixEntity
@Table(name = "MOVIMENTO_FITOSANITARIO", indexes = {
        @Index(name = "IDX_MOVIMENTO_FITOSANITARIO_CAUSALE", columnList = "CAUSALE_ID"),
        @Index(name = "IDX_MOVIMENTO_FITOSANITARIO_FITOSANITARIO", columnList = "FITOSANITARIO_ID"),
        @Index(name = "IDX_MOVIMENTO_FITOSANITARIO_DOCUMENTO_FITOSANITARIO", columnList = "DOCUMENTO_FITOSANITARIO_ID")
})


@Entity
public class MovimentoFitosanitario {
    @Column(name = "ID", nullable = false)
    @Id
    @JmixGeneratedValue
    private Long id;

    @JoinColumn(name = "CAUSALE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CausaliMovimentazioni causale;

    @JoinColumn(name = "FITOSANITARIO_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Fitosanitario fitosanitario;

    @Column(name = "QUANTITA")
    private Double quantita;

    @Column(name = "VALORE")
    private Double valore;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @Column(name = "DATA_", nullable = false)
    @NotNull
    private LocalDate data;

    @Column(name = "QUANTITA_MIN")
    private Double quantitaMin;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "DOCUMENTO_FITOSANITARIO_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DocumentoFitosanitario documentoFitosanitario;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public DocumentoFitosanitario getDocumentoFitosanitario() {
        return documentoFitosanitario;
    }

    public void setDocumentoFitosanitario(DocumentoFitosanitario documentoFitosanitario) {
        this.documentoFitosanitario = documentoFitosanitario;
    }

    public Double getQuantitaMin() {
        return quantitaMin;
    }

    public void setQuantitaMin(Double quantitaMin) {
        this.quantitaMin = quantitaMin;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getValore() {
        return valore;
    }

    public void setValore(Double valore) {
        this.valore = valore;
    }

    public Double getQuantita() {
        return quantita;
    }

    public void setQuantita(Double quantita) {
        this.quantita = quantita;
    }

    public Fitosanitario getFitosanitario() {
        return fitosanitario;
    }

    public void setFitosanitario(Fitosanitario fitosanitario) {
        this.fitosanitario = fitosanitario;
    }

    public CausaliMovimentazioni getCausale() {
        return causale;
    }

    public void setCausale(CausaliMovimentazioni causale) {
        this.causale = causale;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}