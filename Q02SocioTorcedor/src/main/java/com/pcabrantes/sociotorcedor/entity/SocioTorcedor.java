package com.pcabrantes.sociotorcedor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Classe que representa uma entidade de banco de dados para SocioTorcedor
 *
 * @author Paulo Cesar Abrantes
 */
@Entity
public class SocioTorcedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @NotNull
    private Integer idTimeCoracao;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdTimeCoracao() {
        return idTimeCoracao;
    }

    public void setIdTimeCoracao(Integer idTimeCoracao) {
        this.idTimeCoracao = idTimeCoracao;
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
