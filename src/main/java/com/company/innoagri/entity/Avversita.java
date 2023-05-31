package com.company.innoagri.entity;

import io.jmix.core.entity.annotation.CaseConversion;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;

@JmixEntity
@Table(name = "AVVERSITA")
@Entity
public class Avversita {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @CaseConversion
    @Column(name = "NOME")
    private String nome;

    @Column(name = "NOTE")
    @Lob
    private String note;
    @JoinTable(name = "FITOSANITARIO_AVVERSITA_LINK",
            joinColumns = @JoinColumn(name = "AVVERSITA_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "FITOSANITARIO_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Fitosanitario> fitosanitari;
    @JoinTable(name = "PRODOTTO_PIANIFICATO_AVVERSITA_LINK",
            joinColumns = @JoinColumn(name = "AVVERSITA_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODOTTO_PIANIFICATO_ID"))
    @ManyToMany
    private List<ProdottoPianificato> prodottoPianificatoes;

    public List<ProdottoPianificato> getProdottoPianificatoes() {
        return prodottoPianificatoes;
    }

    public void setProdottoPianificatoes(List<ProdottoPianificato> prodottoPianificatoes) {
        this.prodottoPianificatoes = prodottoPianificatoes;
    }

    public List<Fitosanitario> getFitosanitari() {
        return fitosanitari;
    }

    public void setFitosanitari(List<Fitosanitario> fitosanitari) {
        this.fitosanitari = fitosanitari;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}