package com.company.innoagri.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "PRODOTTO_PIANIFICATO", indexes = {
        @Index(name = "IDX_PRODOTTO_PIANIFICATO_FITOSANITARIO", columnList = "FITOSANITARIO_ID"),
        @Index(name = "IDX_PRODOTTO_PIANIFICATO_PIANIFICAZIONE_TRATTAMENTO", columnList = "PIANIFICAZIONE_TRATTAMENTO_ID")
})
@Entity
public class ProdottoPianificato {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InstanceName
    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "FITOSANITARIO_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Fitosanitario fitosanitario;

    @JoinTable(name = "PRODOTTO_PIANIFICATO_AVVERSITA_LINK",
            joinColumns = @JoinColumn(name = "PRODOTTO_PIANIFICATO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AVVERSITA_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Avversita> avversita;

    @JoinTable(name = "PRODOTTO_PIANIFICATO_VARIETA_LINK",
            joinColumns = @JoinColumn(name = "PRODOTTO_PIANIFICATO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "VARIETA_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Varieta> varieta;

    @JoinTable(name = "PRODOTTO_PIANIFICATO_APPEZZAMENTO_LINK",
            joinColumns = @JoinColumn(name = "PRODOTTO_PIANIFICATO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "APPEZZAMENTO_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Appezzamento> appezzamenti;

    @Column(name = "DOSE_ETTARO")
    private Double doseEttaro;

    @Column(name = "DOSE_ETTARO_MIN")
    private Double doseEttaroMin;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @Column(name = "QUANTITA")
    private Double quantita;

    @Column(name = "TOT_ETTARI")
    private String totEttari;

    @Column(name = "QUANTITA_MIN")
    private Double quantitaMin;

    @JmixGeneratedValue
    @Column(name = "UUID")
    private UUID uuid;

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

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PIANIFICAZIONE_TRATTAMENTO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private PianificazioneTrattamento pianificazioneTrattamento;

    @Column(name = "TIPOLOGIA_APPEZZAMENTO")
    private String tipologiaAppezzamento;

    public TipologiaAppezzamento getTipologiaAppezzamento() {
        return tipologiaAppezzamento == null ? null : TipologiaAppezzamento.fromId(tipologiaAppezzamento);
    }

    public void setTipologiaAppezzamento(TipologiaAppezzamento tipologiaAppezzamento) {
        this.tipologiaAppezzamento = tipologiaAppezzamento == null ? null : tipologiaAppezzamento.getId();
    }

    public PianificazioneTrattamento getPianificazioneTrattamento() {
        return pianificazioneTrattamento;
    }

    public void setPianificazioneTrattamento(PianificazioneTrattamento pianificazioneTrattamento) {
        this.pianificazioneTrattamento = pianificazioneTrattamento;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
    public Double getQuantitaMin() {
        return quantitaMin;
    }

    public void setQuantitaMin(Double quantitaMin) {
        this.quantitaMin = quantitaMin;
    }

    public String getTotEttari() {
        return totEttari;
    }

    public void setTotEttari(String totEttari) {
        this.totEttari = totEttari;
    }

    public Double getQuantita() {
        return quantita;
    }

    public void setQuantita(Double quantita) {
        this.quantita = quantita;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getDoseEttaroMin() {
        return doseEttaroMin;
    }

    public void setDoseEttaroMin(Double doseEttaroMin) {
        this.doseEttaroMin = doseEttaroMin;
    }

    public Double getDoseEttaro() {
        return doseEttaro;
    }

    public void setDoseEttaro(Double doseEttaro) {
        this.doseEttaro = doseEttaro;
    }

    public List<Appezzamento> getAppezzamenti() {
        return appezzamenti;
    }

    public void setAppezzamenti(List<Appezzamento> appezzamenti) {
        this.appezzamenti = appezzamenti;
    }

    public List<Varieta> getVarieta() {
        return varieta;
    }

    public void setVarieta(List<Varieta> varieta) {
        this.varieta = varieta;
    }

    public List<Avversita> getAvversita() {
        return avversita;
    }

    public void setAvversita(List<Avversita> avversita) {
        this.avversita = avversita;
    }

    public Fitosanitario getFitosanitario() {
        return fitosanitario;
    }

    public void setFitosanitario(Fitosanitario fitosanitario) {
        this.fitosanitario = fitosanitario;
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