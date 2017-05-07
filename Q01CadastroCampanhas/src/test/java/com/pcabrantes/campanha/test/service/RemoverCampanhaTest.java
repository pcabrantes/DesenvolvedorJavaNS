package com.pcabrantes.campanha.test.service;

import com.pcabrantes.campanha.entity.Campanha;
import com.pcabrantes.campanha.service.CampanhaService;
import com.pcabrantes.campanha.test.SpringTest;
import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.dto.DataVigenciaDTO;
import com.pcabrantes.campanha.util.exception.RecursoNaoExistenteException;
import com.pcabrantes.campanha.util.response.MessageResponse;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Steps para os cenários de teste de Remoção de Campanhas
 *
 * @author Paulo Cesar Abrantes
 */
public class RemoverCampanhaTest extends SpringTest {

    @Autowired
    private CampanhaService campanhaService;

    private CampanhaDTO dto = null;
    private MessageResponse response = null;
    private Exception excecao = null;

    @Dado("^que é incluída uma campanha de teste$")
    public void que_é_incluída_uma_campanha_de_teste() throws Throwable {
        dto = new CampanhaDTO();
        dto.setIdTimeCoracao(1);
        dto.setNome("Campanha de Teste");
        DataVigenciaDTO dataDTO = new DataVigenciaDTO();
        dataDTO.setDataInicial("01/01/2020");
        dataDTO.setDataFinal("01/01/2020");
        dto.setDataVigencia(dataDTO);
    }

    @Quando("^invocar o serviço de cadastro de campanhas$")
    public void invocar_o_serviço_de_cadastro_de_campanhas() throws Throwable {
        response = campanhaService.salvar(dto, CampanhaService.INCLUIR);
    }

    @Entao("^a campanha é cadastrada$")
    public void a_campanha_é_cadastrada() throws Throwable {
        assertEquals(new Integer(200), response.getCodigo());
    }

    @Dado("^que é informado o ID da campanha incluída anteriormente$")
    public void que_é_informado_o_ID_da_campanha_incluída_anteriormente() throws Throwable {
        response = campanhaService.consultar();
        dto = response.getDados().get(response.getDados().size()-1);
    }

    @Quando("^o serviço de exclusão é invocado$")
    public void o_serviço_de_exclusão_é_invocado() throws Throwable {
        try {
            response = campanhaService.remover(dto.getId());
        } catch (Exception ex) {
            excecao = ex;
        }

    }

    @Entao("^o registro é excluído$")
    public void o_registro_é_excluído() throws Throwable {
        assertEquals(new Integer(200), response.getCodigo());
    }

    @Dado("^que é informado o ID (\\d+)$")
    public void que_é_informado_o_ID(int id) throws Throwable {
        dto = new CampanhaDTO();
        dto.setId(new Long(id));
    }

    @Entao("^é lançada uma exceção indicando que o recurso não foi encontrado$")
    public void é_lançada_uma_exceção_indicando_que_o_recurso_não_foi_encontrado() throws Throwable {
        assertNotNull(excecao);
        assertTrue(excecao instanceof RecursoNaoExistenteException);
    }
}
