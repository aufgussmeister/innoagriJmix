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
import java.util.Date;
import java.util.List;

@JmixEntity
@Table(name = "FITOSANITARIO", indexes = {
        @Index(name = "IDX_FITOSANITARIO_FITOSANITARIO_NAZIONALE", columnList = "FITOSANITARIO_NAZIONALE_ID")
})
@Entity
public class Fitosanitario {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    @Column(name = "ATTIVO")
    private Boolean attivo;

    @Column(name = "SOSTANZE_ATTIVE", length = 500)
    private String sostanzeAttive;

    @JoinColumn(name = "FITOSANITARIO_NAZIONALE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private FitosanitarioNazionale fitosanitarioNazionale;

    @Column(name = "PRODOTTO")
    private String prodotto;

    @Column(name = "IMPRESA")
    private String impresa;

    @JoinTable(name = "FITOSANITARIO_AVVERSITA_LINK",
            joinColumns = @JoinColumn(name = "FITOSANITARIO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AVVERSITA_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Avversita> avversita;

    @Column(name = "ATTIVITA")
    private String attivita;

    @Column(name = "DOSE_MINIMA")
    private Double doseMinima;

    @Column(name = "DOSE_MASSIMA")
    private Double doseMassima;

    @Column(name = "UNITA_MISUA")
    private String unitaMisua;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @Column(name = "CODICE_FORNITORE")
    private String codiceFornitore;

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

    public String getCodiceFornitore() {
        return codiceFornitore;
    }

    public void setCodiceFornitore(String codiceFornitore) {
        this.codiceFornitore = codiceFornitore;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UM getUnitaMisua() {
        return unitaMisua == null ? null : UM.fromId(unitaMisua);
    }

    public void setUnitaMisua(UM unitaMisua) {
        this.unitaMisua = unitaMisua == null ? null : unitaMisua.getId();
    }

    public Double getDoseMassima() {
        return doseMassima;
    }

    public void setDoseMassima(Double doseMassima) {
        this.doseMassima = doseMassima;
    }

    public Double getDoseMinima() {
        return doseMinima;
    }

    public void setDoseMinima(Double doseMinima) {
        this.doseMinima = doseMinima;
    }

    public String getAttivita() {
        return attivita;
    }

    public void setAttivita(String attivita) {
        this.attivita = attivita;
    }

    public List<Avversita> getAvversita() {
        return avversita;
    }

    public void setAvversita(List<Avversita> avversita) {
        this.avversita = avversita;
    }

    public String getImpresa() {
        return impresa;
    }

    public void setImpresa(String impresa) {
        this.impresa = impresa;
    }

    public String getProdotto() {
        return prodotto;
    }

    public void setProdotto(String prodotto) {
        this.prodotto = prodotto;
    }

    public FitosanitarioNazionale getFitosanitarioNazionale() {
        return fitosanitarioNazionale;
    }

    public void setFitosanitarioNazionale(FitosanitarioNazionale fitosanitarioNazionale) {
        this.fitosanitarioNazionale = fitosanitarioNazionale;
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

    public String getSostanzeAttive() {
        return sostanzeAttive;
    }

    public void setSostanzeAttive(String sostanzeAttive) {
        this.sostanzeAttive = sostanzeAttive;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}