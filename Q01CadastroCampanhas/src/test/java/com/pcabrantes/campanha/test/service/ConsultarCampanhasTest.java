package com.pcabrantes.campanha.test.service;

import com.pcabrantes.campanha.service.CampanhaService;
import com.pcabrantes.campanha.test.SpringTest;
import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.response.MessageResponse;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Steps para teste de Consultar Campanhas Vigentes
 *
 * @author Paulo Cesar Abrantes
 */
public class ConsultarCampanhasTest extends SpringTest {

    @Autowired
    private CampanhaService campanhaService;

    private MessageResponse response = null;

    @Dado("^que o serviço de consulta é invocado$")
    public void que_o_serviço_de_consulta_é_invocado() throws Throwable {
        response = campanhaService.consultar();
    }

    @Entao("^a consulta é executada com sucesso$")
    public void a_consulta_é_executada_com_sucesso() throws Throwable {
        assertEquals(new Integer(200), response.getCodigo());
    }

    @Entao("^a lista de retorno apresenta apenas campanhas vigentes$")
    public void a_lista_de_retorno_apresenta_apenas_campanhas_vigentes() throws Throwable {

        List<CampanhaDTO> campanhas = response.getDados();
        campanhas.forEach(c -> {
            try {
                assertTrue(DATE_FORMAT.parse(c.getDataVigencia().getDataInicial()).compareTo(HOJE) <= 0);
                assertTrue(DATE_FORMAT.parse(c.getDataVigencia().getDataFinal()).compareTo(HOJE) >= 0);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

}
