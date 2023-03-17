package com.company.innoagri.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.Date;

@JmixEntity
@Table(name = "FITOSANITARIO_NAZIONALE")
@Entity
public class FitosanitarioNazionale {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InstanceName
    @Column(name = "PRODOTTO")
    private String prodotto;

    @Column(name = "IMPRESA")
    private String impresa;

    @Column(name = "DATA_REGISTRAZIONE")
    @Temporal(TemporalType.DATE)
    private Date dataRegistrazione;

    @Column(name = "SCADENZA_AUTORIZZAZIONE")
    @Temporal(TemporalType.DATE)
    private Date scadenzaAutorizzazione;

    @Column(name = "INDICAZIONI_DI_PERICOLO")
    private String indicazioniDiPericolo;

    @Column(name = "ATTIVITA")
    private String attivita;

    @Column(name = "CODICE_FORMULAZIONE")
    private String codiceFormulazione;

    @Column(name = "DESCRIZIONE_FORMULAZIONE")
    private String descrizioneFormulazione;

    @Column(name = "SOSTANZE_ATTIVE")
    private String sostanzeAttive;

    @Column(name = "CONTENUTO_PER_CENTO_GRAMMI_DI_PRODOTTO")
    private String contenutoPerCentoGrammiDiProdotto;

    @Column(name = "STATO_AMMINISTRATIVO")
    private String statoAmministrativo;

    @Column(name = "MOTIVO_REVOCA")
    private String motivoRevoca;

    @Column(name = "DATA_DECORRENZA_REVOCA")
    @Temporal(TemporalType.DATE)
    private Date dataDecorrenzaRevoca;

    public Date getDataDecorrenzaRevoca() {
        return dataDecorrenzaRevoca;
    }

    public void setDataDecorrenzaRevoca(Date dataDecorrenzaRevoca) {
        this.dataDecorrenzaRevoca = dataDecorrenzaRevoca;
    }

    public String getMotivoRevoca() {
        return motivoRevoca;
    }

    public void setMotivoRevoca(String motivoRevoca) {
        this.motivoRevoca = motivoRevoca;
    }

    public String getStatoAmministrativo() {
        return statoAmministrativo;
    }

    public void setStatoAmministrativo(String statoAmministrativo) {
        this.statoAmministrativo = statoAmministrativo;
    }

    public String getContenutoPerCentoGrammiDiProdotto() {
        return contenutoPerCentoGrammiDiProdotto;
    }

    public void setContenutoPerCentoGrammiDiProdotto(String contenutoPerCentoGrammiDiProdotto) {
        this.contenutoPerCentoGrammiDiProdotto = contenutoPerCentoGrammiDiProdotto;
    }

    public String getSostanzeAttive() {
        return sostanzeAttive;
    }

    public void setSostanzeAttive(String sostanzeAttive) {
        this.sostanzeAttive = sostanzeAttive;
    }

    public String getDescrizioneFormulazione() {
        return descrizioneFormulazione;
    }

    public void setDescrizioneFormulazione(String descrizioneFormulazione) {
        this.descrizioneFormulazione = descrizioneFormulazione;
    }

    public String getCodiceFormulazione() {
        return codiceFormulazione;
    }

    public void setCodiceFormulazione(String codiceFormulazione) {
        this.codiceFormulazione = codiceFormulazione;
    }

    public String getAttivita() {
        return attivita;
    }

    public void setAttivita(String attivita) {
        this.attivita = attivita;
    }

    public String getIndicazioniDiPericolo() {
        return indicazioniDiPericolo;
    }

    public void setIndicazioniDiPericolo(String indicazioniDiPericolo) {
        this.indicazioniDiPericolo = indicazioniDiPericolo;
    }

    public Date getScadenzaAutorizzazione() {
        return scadenzaAutorizzazione;
    }

    public void setScadenzaAutorizzazione(Date scadenzaAutorizzazione) {
        this.scadenzaAutorizzazione = scadenzaAutorizzazione;
    }

    public Date getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(Date dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}