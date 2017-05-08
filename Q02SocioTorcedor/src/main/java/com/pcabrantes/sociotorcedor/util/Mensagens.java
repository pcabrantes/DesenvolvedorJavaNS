package com.pcabrantes.sociotorcedor.util;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe abstrata contendo as mensagens do sistema
 *
 * @author Paulo Cesar Abrantes
 */
public abstract class Mensagens {

    /**
     * Mapa contendo mensagens associadas a códigos HTTP
     */
    public static Map<HttpStatus,String> MENSAGENS_HTTP = new HashMap<>();

    static {
        MENSAGENS_HTTP.put(HttpStatus.OK, "Operação realizada com sucesso.");
        MENSAGENS_HTTP.put(HttpStatus.BAD_REQUEST, "Requisição inválida ou não pôde ser entregue.");
        MENSAGENS_HTTP.put(HttpStatus.NOT_FOUND, "A URL solicitada ou recurso não existe.");
        MENSAGENS_HTTP.put(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno.");
    }

}
