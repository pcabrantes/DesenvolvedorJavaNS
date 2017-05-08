package com.pcabrantes.streams.exception;

/**
 * Exceção a ser lançada quando a vogal não for encontrada
 *
 * @author Paulo Cesar Abrantes
 */
public class VogalNaoEncontradaException extends Exception {

    public VogalNaoEncontradaException() {
        super("Não foi encontrada uma vogal que esteja de acordo com a regra.");
    }

}
