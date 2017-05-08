package com.pcabrantes.streams;

import com.pcabrantes.streams.exception.VogalNaoEncontradaException;
import com.pcabrantes.streams.processador.Stream;

/**
 * Classe que busca o primeiro caractere Vogal, após uma consoante, onde a mesma é antecessora
 * a uma vogal e não se repite no resto da stream
 *
 * @author Paulo Cesar Abrantes
 */
public abstract class ProcessStream {

    private static final String REGEX_VOGAL = "a|A|e|E|i|I|o|O|u|U";

    private static int step;

    public static char firstChar(Stream input) throws VogalNaoEncontradaException {

        step = 0;

        char atual;
        char encontrado = '\0';

        while (input.hasNext()) {
            atual = input.getNext();

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

    private static boolean isVogal(char c) {
        return String.valueOf(c).matches(REGEX_VOGAL);
    }

}
