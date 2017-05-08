package com.pcabrantes.streams.processador.impl;

import com.pcabrantes.streams.exception.VogalNaoEncontradaException;
import com.pcabrantes.streams.processador.Stream;

/**
 * Classe que implementa a interface Stream
 *
 * @author Paulo Cesar Abrantes
 */
public class StreamImpl implements Stream {

    private static final String REGEX_VOGAL = "A|E|I|O|U";

    private String input;
    private int index;
    private int step;


    public char getNext() {
        return input.charAt(index++);
    }

    public boolean hasNext() {
        return index < input.length();
    }

    private boolean isVogal(char c) {
        return String.valueOf(c).matches(REGEX_VOGAL);
    }

    public char firstChar(String input) throws VogalNaoEncontradaException {

        this.input = input.toUpperCase();
        this.index = 0;
        this.step = 0;

        char atual;
        char encontrado = '\0';

        while (hasNext()) {
            atual = getNext();

            switch (step) {
                case 0:
                    step = isVogal(atual) ? step+1 : step;
                    break;
                case 1:
                    step = !isVogal(atual) ? step+1 : step;
                    break;
                case 2:
                    step = isVogal(atual) ? step+1 : step;
                    encontrado = atual;
                    break;
                case 3:
                    step = atual == encontrado ? 0 : step;
                    break;
            }
        }

        if (step < 3) {
            throw new VogalNaoEncontradaException();
        }

        return encontrado;
    }
}
