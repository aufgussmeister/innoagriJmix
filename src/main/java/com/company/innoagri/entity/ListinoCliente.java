package com.company.innoagri.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
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
import java.util.Date;

@JmixEntity
@Table(name = "LISTINO_CLIENTE", indexes = {
        @Index(name = "IDX_LISTINO_CLIENTE_LAVORAZIONE", columnList = "LAVORAZIONE_ID"),
        @Index(name = "IDX_LISTINO_CLIENTE_CLIENTE", columnList = "CLIENTE_ID")
})
@Entity
public class ListinoCliente {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    @NotNull
    @JoinColumn(name = "LAVORAZIONE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lavorazione lavorazione;

    @Column(name = "SCONTO")
    private Double sconto = 0.0;

    @Column(name = "PREZZO_CLIENTE")
    private Double prezzoCliente;

    @Column(name = "PREZZO_UM")
    private Double prezzoUm;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

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

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "CLIENTE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    public Double getPrezzoUm() {
        return prezzoUm;
    }

    public void setPrezzoUm(Double prezzoUm) {
        this.prezzoUm = prezzoUm;
    }

    public Double getPrezzoCliente() {
        return prezzoCliente;
    }

    public void setPrezzoCliente(Double prezzoCliente) {
        this.prezzoCliente = prezzoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSconto() {
        return sconto;
    }

    public void setSconto(Double sconto) {
        this.sconto = sconto;
    }

    public Lavorazione getLavorazione() {
        return lavorazione;
    }

    public void setLavorazione(Lavorazione lavorazione) {
        this.lavorazione = lavorazione;
    }

    public String getTenant() { return tenant; }

    public void setTenant(String value) {
        this.tenant = value;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}


