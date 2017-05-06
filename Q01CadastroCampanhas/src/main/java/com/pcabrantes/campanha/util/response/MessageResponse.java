package com.pcabrantes.campanha.util.response;

/**
 * Classe que representa uma resposta dos serviços REST
 *
 * @author Paulo Cesar Abrantes
 */
public class MessageResponse {

    private Integer codigo;
    private String mensagem;
    private Object dados;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getDados() {
        return dados;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }
}
