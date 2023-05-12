package com.company.innoagri.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.CaseConversion;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.Composition;
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
import java.util.Objects;

@JmixEntity
@Table(name = "CLIENTE")
@Entity
public class Cliente {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    @OneToMany(mappedBy = "cliente")
    private List<Fattura> fatture;

    @Column(name = "SOCIETA")
    private Boolean societa;

    @Column(name = "DISATTIVATO")
    private Boolean disattivato;

    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    @OneToMany(mappedBy = "cliente")
    private List<ListinoCliente> listini;

    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    @OneToMany(mappedBy = "cliente")
    private List<Appezzamento> appezzamenti;

    @CaseConversion
    @Column(name = "RAGIONE_SOCIALE")
    private String ragioneSociale;

    @CaseConversion
    @Column(name = "NOME")
    private String nome;

    @CaseConversion
    @Column(name = "COGNOME")
    private String cognome;

    @CaseConversion
    @Column(name = "PIVA")
    private String piva;

    @CaseConversion
    @Column(name = "CODICE_FISCALE")
    private String codiceFiscale;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "CELLULARE")
    private String cellulare;

    @Column(name = "LOCALITA")
    private String localita;

    @Column(name = "INDIRIZZO")
    private String indirizzo;

    @Column(name = "COMUNE")
    private String comune;

    @Column(name = "CAP", length = 5)
    private String cap;

    @CaseConversion
    @Column(name = "PROVINCIA", length = 2)
    private String provincia;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PEC")
    private String pec;

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

    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    @OneToMany(mappedBy = "cliente")
    private List<Attivita> attivita;

    public Boolean getDisattivato() {
        return disattivato;
    }

    public void setDisattivato(Boolean disattivato) {
        this.disattivato = disattivato;
    }

    public List<Attivita> getAttivita() {
        return attivita;
    }

    public void setAttivita(List<Attivita> attivita) {
        this.attivita = attivita;
    }

    public List<Fattura> getFatture() {
        return fatture;
    }

    public void setFatture(List<Fattura> fatture) {
        this.fatture = fatture;
    }

    public List<ListinoCliente> getListini() {
        return listini;
    }

    public void setListini(List<ListinoCliente> listini) {
        this.listini = listini;
    }

    public Boolean getSocieta() {
        return societa;
    }

    public void setSocieta(Boolean societa) {
        this.societa = societa;
    }

    public List<Appezzamento> getAppezzamenti() {
        return appezzamenti;
    }

    public void setAppezzamenti(List<Appezzamento> appezzamenti) {
        this.appezzamenti = appezzamenti;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getPiva() {
        return piva;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
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


    @DependsOnProperties({"nome", "cognome"})
    public String getDisplayName() {
        return String.format("%s %s ", (cognome != null ? cognome : ""),
                (nome != null ? nome : "")).trim();
    }

    @InstanceName
    @DependsOnProperties({"ragioneSociale", "nome", "cognome"})
    public String getCliente() {
        if(Objects.nonNull(this.ragioneSociale) && !this.ragioneSociale.isEmpty())
            return this.ragioneSociale;
        else
            return this.nome + " " + this.cognome;

    }

}