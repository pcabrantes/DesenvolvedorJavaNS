package com.pcabrantes.sociotorcedor.util.exception;

import com.pcabrantes.sociotorcedor.util.Mensagens;
import org.springframework.http.HttpStatus;

/**
 * Classe utilizada para representar uma exceção indicando que o serviço de campanhas está indisponível
 *
 * @author Paulo Cesar Abrantes
 */
public class ServicoIndisponivelException extends Exception {

    public ServicoIndisponivelException() {
        super(Mensagens.MENSAGENS_HTTP.get(HttpStatus.SERVICE_UNAVAILABLE));
    }

}
