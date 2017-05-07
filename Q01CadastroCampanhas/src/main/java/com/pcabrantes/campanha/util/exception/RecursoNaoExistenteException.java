package com.pcabrantes.campanha.util.exception;

import com.pcabrantes.campanha.util.Mensagens;
import org.springframework.http.HttpStatus;

/**
 * Classe que representa uma exceção a ser lançada ao tentar acessar um recurso que não existe
 *
 * @author Paulo Cesar Abrantes
 */
public class RecursoNaoExistenteException extends Exception {

    public RecursoNaoExistenteException() {
        super(Mensagens.MENSAGENS_HTTP.get(HttpStatus.NOT_FOUND));
    }
}
