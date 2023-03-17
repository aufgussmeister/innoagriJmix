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
import java.util.Date;
import java.util.List;

@JmixEntity
@Table(name = "APPEZZAMENTO", indexes = {
        @Index(name = "IDX_APPEZZAMENTO_VARIETA", columnList = "VARIETA_ID"),
        @Index(name = "IDX_APPEZZAMENTO_CLIENTE", columnList = "CLIENTE_ID")
})
@Entity
public class Appezzamento {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    @Column(name = "DENOMINAZIONE")
    private String denominazione;

    @Column(name = "COMUNE")
    private String comune;

    @Column(name = "ANNO_IMPIANTO")
    private Integer annoImpianto;

    @Column(name = "ANNO_SOVRAINNESTO")
    private Integer annoSovrainnesto;

    @Column(name = "SUPERFICIE")
    private Integer superficie;

    @Column(name = "DISTANZA_ACQUA")
    private Double distanzaAcqua;

    @Column(name = "DISTANZA_TRA_PIANTE")
    private Double distanzaTraPiante;

    @Column(name = "DISTANZA_TRA_FILARI")
    private Double distanzaTraFilari;

    @Column(name = "IRRIGATO")
    private Boolean irrigato;

    @Column(name = "TIPO_IRRIGAZIONE")
    private String tipoIrrigazione;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @JoinColumn(name = "VARIETA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Varieta varieta;

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
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cliente cliente;
    @JoinTable(name = "ATTIVITA_APPEZZAMENTO_LINK",
            joinColumns = @JoinColumn(name = "APPEZZAMENTO_ID"),
            inverseJoinColumns = @JoinColumn(name = "ATTIVITA_ID"))
    @ManyToMany
    private List<Attivita> attivita;

    public List<Attivita> getAttivita() {
        return attivita;
    }

    public void setAttivita(List<Attivita> attivita) {
        this.attivita = attivita;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public Varieta getVarieta() {
        return varieta;
    }

    public void setVarieta(Varieta varieta) {
        this.varieta = varieta;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTipoIrrigazione() {
        return tipoIrrigazione;
    }

    public void setTipoIrrigazione(String tipoIrrigazione) {
        this.tipoIrrigazione = tipoIrrigazione;
    }

    public Boolean getIrrigato() {
        return irrigato;
    }

    public void setIrrigato(Boolean irrigato) {
        this.irrigato = irrigato;
    }

    public Double getDistanzaTraFilari() {
        return distanzaTraFilari;
    }

    public void setDistanzaTraFilari(Double distanzaTraFilari) {
        this.distanzaTraFilari = distanzaTraFilari;
    }

    public Double getDistanzaTraPiante() {
        return distanzaTraPiante;
    }

    public void setDistanzaTraPiante(Double distanzaTraPiante) {
        this.distanzaTraPiante = distanzaTraPiante;
    }

    public Double getDistanzaAcqua() {
        return distanzaAcqua;
    }

    public void setDistanzaAcqua(Double distanzaAcqua) {
        this.distanzaAcqua = distanzaAcqua;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public Integer getAnnoSovrainnesto() {
        return annoSovrainnesto;
    }

    public void setAnnoSovrainnesto(Integer annoSovrainnesto) {
        this.annoSovrainnesto = annoSovrainnesto;
    }

    public Integer getAnnoImpianto() {
        return annoImpianto;
    }

    public void setAnnoImpianto(Integer annoImpianto) {
        this.annoImpianto = annoImpianto;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
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