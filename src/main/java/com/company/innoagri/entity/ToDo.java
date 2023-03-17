package com.company.innoagri.entity;

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JmixEntity
@Table(name = "TO_DO", indexes = {
        @Index(name = "IDX_TO_DO_CREATO_DA", columnList = "CREATO_DA_ID")
})
@Entity
public class ToDo {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @JoinColumn(name = "CREATO_DA_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User creatoDa;

    @JoinTable(name = "TO_DO_USER_LINK",
            joinColumns = @JoinColumn(name = "TO_DO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<User> assegnatoA;

    @Column(name = "SCADENZA")
    @Temporal(TemporalType.DATE)
    private Date scadenza;

    @Column(name = "FATTO")
    private Boolean fatto;

    @Column(name = "PRESO_IN_CARICO")
    private Boolean presoInCarico;

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

    public Boolean getPresoInCarico() {
        return presoInCarico;
    }

    public void setPresoInCarico(Boolean presoInCarico) {
        this.presoInCarico = presoInCarico;
    }

    public Boolean getFatto() {
        return fatto;
    }

    public void setFatto(Boolean fatto) {
        this.fatto = fatto;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public List<User> getAssegnatoA() {
        return assegnatoA;
    }

    public void setAssegnatoA(List<User> assegnatoA) {
        this.assegnatoA = assegnatoA;
    }

    public User getCreatoDa() {
        return creatoDa;
    }

    public void setCreatoDa(User creatoDa) {
        this.creatoDa = creatoDa;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    public String getTenant() { return tenant; }

    public void setTenant(String tenant) { this.tenant = tenant; }

}