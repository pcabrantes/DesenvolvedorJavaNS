package com.pcabrantes.campanha.test.service;

import com.pcabrantes.campanha.service.CampanhaService;
import com.pcabrantes.campanha.test.SpringTest;
import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.exception.RecursoNaoExistenteException;
import com.pcabrantes.campanha.util.response.MessageResponse;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Steps para teste de Consultar Campanhas Vigentes
 *
 * @author Paulo Cesar Abrantes
 */
public class ConsultarCampanhasTest extends SpringTest {

    @Autowired
    private CampanhaService campanhaService;

    private Long id = null;
    private MessageResponse response = null;
    private Exception excecao = null;

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

    @Dado("^que é informada uma campanha com ID (\\d+)$")
    public void que_é_informada_uma_campanha_com_ID(int id) throws Throwable {
        this.id = new Long(id);
    }

    @Quando("^a consulta por ID é invocada$")
    public void a_consulta_por_ID_é_invocada() throws Throwable {
        try {
            response = campanhaService.consultar(id);
        } catch (Exception ex) {
            excecao = ex;
        }
    }

    @Entao("^apenas o registro consultado é retornado$")
    public void apenas_o_registro_consultado_é_retornado() throws Throwable {
        assertEquals(id, response.getDados().get(0).getId());
        assertEquals(new Integer(1), new Integer(response.getDados().size()));
    }

    @Entao("^é lançada uma exceção indicando que a campanha não foi encontrada$")
    public void é_lançada_uma_exceção_indicando_que_a_campanha_não_foi_encontrada() throws Throwable {
        assertNotNull(excecao);
        assertTrue(excecao instanceof RecursoNaoExistenteException);
    }

    @Dado("^que é informado um time com id (\\d+)$")
    public void que_é_informado_um_time_com_id(int id) throws Throwable {
        this.id = new Long(id);
    }

    @Quando("^a consulta por time é invocada$")
    public void a_consulta_por_time_é_invocada() throws Throwable {
        try {
            response = campanhaService.consultarPorTime(id.intValue());
        } catch (Exception ex) {
            excecao = ex;
        }
    }

    @Entao("^sao retornadas campanhas vigentes associadas ao time$")
    public void sao_retornadas_campanhas_vigentes_associadas_ao_time() throws Throwable {
        List<CampanhaDTO> campanhas = response.getDados().parallelStream()
                .filter(c->!c.getIdTimeCoracao().equals(id.intValue()))
                .collect(Collectors.toList());

        assertTrue(campanhas.isEmpty());
    }
}
