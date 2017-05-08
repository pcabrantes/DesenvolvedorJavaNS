package com.pcabrantes.streams.processador;

/**
 * Interface que define os metodos de uma Stream
 *
 * @author Paulo Cesar Abrantes
 */
public interface Stream {

    /**
     * Obtem o proximo caractere da stream
     *
     * @return o proximo caractere
     */
    char getNext();

    /**
     * Verifica se existe um proximo caractere a ser processado
     *
     * @return true | false
     */
    boolean hasNext();
}
