package com.pcabrantes.sociotorcedor.util.response;

import com.pcabrantes.sociotorcedor.client.dto.CampanhaDTO;
import com.pcabrantes.sociotorcedor.util.Mensagens;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Classe que representa uma resposta dos serviços REST
 *
 * @author Paulo Cesar Abrantes
 */
public class MessageResponse {

    private Integer codigo;
    private String mensagem;
    private List<CampanhaDTO> dados;

    public MessageResponse() {}

    public MessageResponse(HttpStatus status) {
        this.codigo = status.value();
        this.mensagem = Mensagens.MENSAGENS_HTTP.get(status);
    }

    public MessageResponse(HttpStatus status, List<CampanhaDTO> dados) {
        this(status);
        this.setDados(dados);
    }

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

    public List<CampanhaDTO> getDados() {
        return dados;
    }

    public void setDados(List<CampanhaDTO> dados) {
        this.dados = dados;
    }
}
