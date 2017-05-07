package com.pcabrantes.campanha.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Classe que representa uma entidade de banco de dados referente a uma campanha
 *
 * @author Paulo Cesar Abrantes
 */
@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull @Min(1)
    private Integer idTimeCoracao;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataInicial;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataFinal;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    private boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdTimeCoracao() {
        return idTimeCoracao;
    }

    public void setIdTimeCoracao(Integer idTimeCoracao) {
        this.idTimeCoracao = idTimeCoracao;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
