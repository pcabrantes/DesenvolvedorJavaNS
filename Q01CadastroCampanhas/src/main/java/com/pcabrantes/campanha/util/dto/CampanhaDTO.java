package com.pcabrantes.campanha.util.dto;

/**
 * Classe que representa o objeto JSON da campanha usada na requisição/resposta dos serviços
 *
 * @author Paulo Cesar Abrantes
 */
public class CampanhaDTO {

    private Long id;
    private String nome;
    private Integer idTimeCoracao;
    private DataVigenciaDTO dataVigencia;

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

    public DataVigenciaDTO getDataVigencia() {
        return dataVigencia;
    }

    public void setDataVigencia(DataVigenciaDTO dataVigencia) {
        this.dataVigencia = dataVigencia;
    }
}
