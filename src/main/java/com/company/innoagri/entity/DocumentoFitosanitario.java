package com.company.innoagri.entity;

import io.jmix.core.annotation.TenantId;
import io.jmix.core.metamodel.annotation.Composition;
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
@Table(name = "DOCUMENTO_FITOSANITARIO", indexes = {
        @Index(name = "IDX_DOCUMENTO_FITOSANITARIO_CLIENTE", columnList = "CLIENTE_ID"),
        @Index(name = "IDX_DOCUMENTO_FITOSANITARIO_FORNITORE", columnList = "FORNITORE_ID")
})
@Entity
public class DocumentoFitosanitario {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIPO_DOCUMENTO", nullable = false)
    @NotNull
    private String tipoDocumento;

    @Composition
    @OneToMany(mappedBy = "documentoFitosanitario")
    private List<MovimentoFitosanitario> movimenti;

    @JoinColumn(name = "CLIENTE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @JoinColumn(name = "FORNITORE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fornitore fornitore;

    @Column(name = "DATA_", nullable = false)
    @NotNull
    private LocalDate data;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @Column(name = "RIFERIMENTO_DOCUMENTO_ESTERNO")
    private String riferimentoDocumentoEsterno;

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

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getRiferimentoDocumentoEsterno() {
        return riferimentoDocumentoEsterno;
    }

    public void setRiferimentoDocumentoEsterno(String riferimentoDocumentoEsterno) {
        this.riferimentoDocumentoEsterno = riferimentoDocumentoEsterno;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<MovimentoFitosanitario> getMovimenti() {
        return movimenti;
    }

    public void setMovimenti(List<MovimentoFitosanitario> movimenti) {
        this.movimenti = movimenti;
    }

    public TipoDocumentoFitosanitario getTipoDocumento() {
        return tipoDocumento == null ? null : TipoDocumentoFitosanitario.fromId(tipoDocumento);
    }

    public void setTipoDocumento(TipoDocumentoFitosanitario tipoDocumento) {
        this.tipoDocumento = tipoDocumento == null ? null : tipoDocumento.getId();
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