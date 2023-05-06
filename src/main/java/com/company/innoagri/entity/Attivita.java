package com.company.innoagri.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
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

@JmixEntity
@Table(name = "ATTIVITA", indexes = {
        @Index(name = "IDX_ATTIVITA_COLLABORATORE", columnList = "COLLABORATORE_ID"),
        @Index(name = "IDX_ATTIVITA_LAVORAZIONE", columnList = "LAVORAZIONE_ID"),
        @Index(name = "IDX_ATTIVITA_MEZZO", columnList = "MEZZO_ID"),
        @Index(name = "IDX_ATTIVITA_FATTURA", columnList = "FATTURA_ID"),
        @Index(name = "IDX_ATTIVITA_CLIENTE", columnList = "CLIENTE_ID")
})
@Entity
public class Attivita {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cliente cliente;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    @Column(name = "ORE_COLLABORATORE")
    private Double oreCollaboratore;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @JoinColumn(name = "COLLABORATORE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User collaboratore;

    @Column(name = "QTA")
    private Double qta;

    @Column(name = "ORA_INIZIO")
    @Temporal(TemporalType.TIME)
    private Date oraInizio;

    @Column(name = "ORA_FINE")
    @Temporal(TemporalType.TIME)
    private Date oraFine;

    @Column(name = "DURATA")
    private Double durata;

    @Column(name = "VALORE")
    private Double valore;

    @Column(name = "UNITA_MISURA")
    private String unitaMisura;

    @JoinColumn(name = "LAVORAZIONE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lavorazione lavorazione;

    @NotNull
    @Column(name = "DATA_", nullable = false)
    private LocalDate data;

    @OnDelete(DeletePolicy.UNLINK)
    @JoinTable(name = "ATTIVITA_APPEZZAMENTO_LINK",
            joinColumns = @JoinColumn(name = "ATTIVITA_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "APPEZZAMENTO_ID", referencedColumnName = "ID"))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Appezzamento> appezzamenti;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    @Column(name = "PREZZO")
    private Double prezzo;

    @Column(name = "VERIFICATO")
    private Boolean verificato;

    @Column(name = "DA_FATTURARE")
    private Boolean daFatturare;

    @Column(name = "FATTURATO")
    private Boolean fatturato;

    @JoinColumn(name = "MEZZO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mezzo mezzo;

    @JoinColumn(name = "FATTURA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fattura fattura;

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

    public void setAppezzamenti(List<Appezzamento> appezzamenti) {
        this.appezzamenti = appezzamenti;
    }

    public List<Appezzamento> getAppezzamenti() {
        return appezzamenti;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Fattura getFattura() {
        return fattura;
    }

    public void setFattura(Fattura fattura) {
        this.fattura = fattura;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Boolean getFatturato() {
        return fatturato;
    }

    public void setFatturato(Boolean fatturato) {
        this.fatturato = fatturato;
    }

    public Boolean getDaFatturare() {
        return daFatturare;
    }

    public void setDaFatturare(Boolean daFatturare) {
        this.daFatturare = daFatturare;
    }

    public Boolean getVerificato() {
        return verificato;
    }

    public void setVerificato(Boolean verificato) {
        this.verificato = verificato;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Lavorazione getLavorazione() {
        return lavorazione;
    }

    public void setLavorazione(Lavorazione lavorazione) {
        this.lavorazione = lavorazione;
    }

    public UM getUnitaMisura() {
        return unitaMisura == null ? null : UM.fromId(unitaMisura);
    }

    public void setUnitaMisura(UM unitaMisura) {
        this.unitaMisura = unitaMisura == null ? null : unitaMisura.getId();
    }

    public Double getValore() {
        return valore;
    }

    public void setValore(Double valore) {
        this.valore = valore;
    }

    public Double getDurata() {
        return durata;
    }

    public void setDurata(Double durata) {
        this.durata = durata;
    }

    public Date getOraFine() {
        return oraFine;
    }

    public void setOraFine(Date oraFine) {
        this.oraFine = oraFine;
    }

    public Date getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Date oraInizio) {
        this.oraInizio = oraInizio;
    }

    public Double getQta() {
        return qta;
    }

    public void setQta(Double qta) {
        this.qta = qta;
    }

    public User getCollaboratore() {
        return collaboratore;
    }

    public void setCollaboratore(User collaboratore) {
        this.collaboratore = collaboratore;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getOreCollaboratore() {
        return oreCollaboratore;
    }

    public void setOreCollaboratore(Double oreCollaboratore) {
        this.oreCollaboratore = oreCollaboratore;
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