package com.pcabrantes.streams.test;

import com.pcabrantes.streams.ProcessStream;
import com.pcabrantes.streams.exception.VogalNaoEncontradaException;
import com.pcabrantes.streams.processador.Stream;
import com.pcabrantes.streams.processador.impl.StreamImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pc on 08/05/17.
 */
public class ProcessStreamTest {

    @Test(expected = VogalNaoEncontradaException.class)
    public void testApenasConsoantes() throws VogalNaoEncontradaException {
        Stream stream = new StreamImpl("bbbbbbbbbbb");
        ProcessStream.firstChar(stream);
    }

    @Test(expected = VogalNaoEncontradaException.class)
    public void testApenasVogais() throws VogalNaoEncontradaException {
        Stream stream = new StreamImpl("aeiou");
        ProcessStream.firstChar(stream);
    }

    @Test(expected = VogalNaoEncontradaException.class)
    public void testVogalRepetida() throws VogalNaoEncontradaException {
        Stream stream = new StreamImpl("caferufurhfure");
        ProcessStream.firstChar(stream);
    }

    @Test
    public void testApenasPadrao() throws VogalNaoEncontradaException {
        Stream stream = new StreamImpl("afe");
        assertEquals('e', ProcessStream.firstChar(stream));
    }

    @Test
    public void testPadraoAcrescidoDeTexto() throws VogalNaoEncontradaException {
        Stream stream = new StreamImpl("afeggggwww");
        assertEquals('e', ProcessStream.firstChar(stream));
    }

    @Test
    public void testMaisDeUmPadrao() throws VogalNaoEncontradaException {
        Stream stream = new StreamImpl("afeggggwwwufa");
        assertEquals('e', ProcessStream.firstChar(stream));
    }

    @Test
    public void testPadraoNoFinal() throws VogalNaoEncontradaException {
        Stream stream = new StreamImpl("ggggwwwufa");
        assertEquals('a', ProcessStream.firstChar(stream));
    }
}
