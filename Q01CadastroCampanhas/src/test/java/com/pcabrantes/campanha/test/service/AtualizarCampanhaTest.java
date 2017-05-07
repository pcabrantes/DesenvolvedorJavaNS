package com.pcabrantes.campanha.test.service;

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

import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 *
 * Steps para os cenários de teste de Atualizar Campanha
 *
 * @author  Paulo Cesar Abrantes
 */
public class AtualizarCampanhaTest extends SpringTest {

    @Autowired
    private CampanhaService campanhaService;

    private MessageResponse response;
    private Integer qtd = 0;
    private CampanhaDTO dto = null;
    private Exception excecao = null;

    @Dado("^que são consultadas todas as campanhas$")
    public void que_são_consultadas_todas_as_campanhas() throws Throwable {
        response = campanhaService.consultar();
        qtd = response.getDados().size();
    }

    @Quando("^adicionar ao final de todos os nomes o texto \"([^\"]*)\", alterar o time para (\\d+) e invocar o serviço de atualizar$")
    public void adicionar_ao_final_de_todos_os_nomes_o_texto_alterar_o_time_para_e_invocar_o_serviço_de_atualizar(String sufixo, int arg2) throws Throwable {
        response.getDados().parallelStream().forEach(c->{
            c.setNome(c.getNome() + " " + sufixo);
            c.setIdTimeCoracao(10);
            try {
                response = campanhaService.salvar(c, CampanhaService.ATUALIZAR);
                assertEquals(new Integer(200), response.getCodigo());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Entao("^os dados informados são atualizados$")
    public void os_dados_informados_são_atualizados() throws Throwable {
        response = campanhaService.consultar();
        boolean possuiNaoAlterados = response.getDados().parallelStream()
                .filter(c->!c.getIdTimeCoracao().equals(10) && !c.getNome().contains("(Atualizado)"))
                .collect(Collectors.toList()).size() > 0;
        assertFalse(possuiNaoAlterados);
    }

    @Entao("^a quantidade de resultados permanece a mesma$")
    public void a_quantidade_de_resultados_permanece_a_mesma() throws Throwable {
        assertEquals(qtd, new Integer(response.getDados().size()));
    }

    @Dado("^que é montado um objeto com id inexistente$")
    public void que_é_montado_um_objeto_com_id_inexistente() throws Throwable {
        dto = new CampanhaDTO();
        dto.setId(99999L);
        dto.setIdTimeCoracao(1);
        dto.setNome("Campanha de Teste");
        DataVigenciaDTO dataDTO = new DataVigenciaDTO();
        dataDTO.setDataInicial("01/01/2017");
        dataDTO.setDataFinal("01/01/2017");
        dto.setDataVigencia(dataDTO);
    }


    @Quando("^é invocado o serviço de atualizar campanhas$")
    public void é_invocado_o_serviço_de_atualizar_campanhas() throws Throwable {
        try {
            campanhaService.salvar(dto, CampanhaService.ATUALIZAR);
        } catch (Exception ex) {
            excecao = ex;
        }

    }

    @Entao("^é lançada uma exceção do tipo RecursoNaoEncontradoException$")
    public void é_lançada_uma_exceção_do_tipo_RecursoNaoEncontradoException() throws Throwable {
        assertNotNull(excecao);
        assertTrue(excecao instanceof RecursoNaoExistenteException);
        excecao = null;
    }

    @Dado("^que é montado um objeto sem id$")
    public void que_é_montado_um_objeto_sem_id() throws Throwable {
        dto = new CampanhaDTO();
        dto.setIdTimeCoracao(1);
        dto.setNome("Campanha de Teste");
        DataVigenciaDTO dataDTO = new DataVigenciaDTO();
        dataDTO.setDataInicial("01/01/2017");
        dataDTO.setDataFinal("01/01/2017");
        dto.setDataVigencia(dataDTO);
    }

    @Entao("^é lançada uma exceção IllegalArgumentException$")
    public void é_lançada_uma_exceção_IllegalArgumentException() throws Throwable {
        assertNotNull(excecao);
        assertTrue(excecao instanceof IllegalArgumentException);
        excecao = null;
    }

}
