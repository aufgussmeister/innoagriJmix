package com.company.innoagri.entity;

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.CaseConversion;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@JmixEntity
@Table(name = "VARIETA")
@Entity
public class Varieta {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    @InstanceName
    @CaseConversion
    @Column(name = "VARIETA")
    private String varieta;

    @Column(name = "NOTE")
    @Lob
    private String note;

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

    @JoinTable(name = "PRODOTTO_PIANIFICATO_VARIETA_LINK",
            joinColumns = @JoinColumn(name = "VARIETA_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODOTTO_PIANIFICATO_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<ProdottoPianificato> prodottoPianificatoes;

    public List<ProdottoPianificato> getProdottoPianificatoes() {
        return prodottoPianificatoes;
    }

    public void setProdottoPianificatoes(List<ProdottoPianificato> prodottoPianificatoes) {
        this.prodottoPianificatoes = prodottoPianificatoes;
    }

    public String getTenant() { return tenant; }

    public void setTenant(String value) {
        this.tenant = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getVarieta() {
        return varieta;
    }

    public void setVarieta(String varieta) {
        this.varieta = varieta;
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

    @DependsOnProperties({"varieta"})
    public String getInstanceName() {
        return String.format("%s", varieta);
    }
}