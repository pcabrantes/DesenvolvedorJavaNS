package com.pcabrantes.sociotorcedor.util.dto;

/**
 * DTO utilizado para representar o JSON com os dados do Sócio Torcedor
 *
 * @author Paulo Cesar Abrantes
 */
public class SocioTorcedorDTO {

    private String nome;
    private String email;
    private String dataNascimento;
    private String idTimeCoracao;

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getIdTimeCoracao() {
        return idTimeCoracao;
    }

    public void setIdTimeCoracao(String idTimeCoracao) {
        this.idTimeCoracao = idTimeCoracao;
    }
}
