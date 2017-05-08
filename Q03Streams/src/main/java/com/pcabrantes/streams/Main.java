package com.pcabrantes.streams;

import com.pcabrantes.streams.exception.VogalNaoEncontradaException;
import com.pcabrantes.streams.processador.Stream;
import com.pcabrantes.streams.processador.impl.StreamImpl;

/**
 * Created by pcesar on 08/05/17.
 */
public class Main {

    public static void main(String[] args) throws VogalNaoEncontradaException {
        StreamImpl stream = new StreamImpl();
        System.out.println(stream.firstChar("ufaaecabioue"));
    }
}
