package com.pcabrantes.streams.processador.impl;

import com.pcabrantes.streams.processador.Stream;

/**
 * Classe que implementa a interface Stream
 *
 * @author Paulo Cesar Abrantes
 */
public class StreamImpl implements Stream {

    private String input;
    private int index;

    public StreamImpl(String input) {
        this.setInput(input);
    }

    public char getNext() {
        return getInput().charAt(index++);
    }

    public boolean hasNext() {
        return index < getInput().length();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
