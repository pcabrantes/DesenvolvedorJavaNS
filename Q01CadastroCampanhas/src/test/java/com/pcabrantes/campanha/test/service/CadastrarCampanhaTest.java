package com.pcabrantes.campanha.test.service;

import com.pcabrantes.campanha.service.CampanhaService;
import com.pcabrantes.campanha.test.SpringTest;
import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.response.MessageResponse;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Steps para os cenários de teste de Cadastrar uma Campanha
 *
 * @author Paulo Cesar Abrantes
 */
public class CadastrarCampanhaTest extends SpringTest {

    @Autowired
    private CampanhaService campanhaService;

    private CampanhaDTO dto = null;
    private MessageResponse response = null;
    private Exception excecao = null;

    @Dado("^que é informado o nome \"([^\"]*)\", o Time (\\d+) e a vigência de \"([^\"]*)\" a \"([^\"]*)\"$")
    public void que_é_informado_o_nome_o_Time_e_a_vigência_de_a(String nome, int time, String dtInicio, String dtFim) throws Throwable {

        dto = criarDTO(nome, time, dtInicio, dtFim);
    }

    @Quando("^é invocado o serviço de cadastro de campanhas$")
    public void é_invocado_o_serviço_de_cadastro_de_campanhas() throws Throwable {
        try {
            response = campanhaService.salvar(dto, CampanhaService.INCLUIR);
        } catch (Exception ex) {
            excecao = ex;
        }

    }

    @Entao("^é retornado código HTTP (\\d+)$")
    public void é_retornado_código_HTTP(int codigo) throws Throwable {
        assertEquals(new Integer(codigo), response.getCodigo());
    }


    @Entao("^a data final da campanha já cadastrada é alterada para \"([^\"]*)\"$")
    public void a_data_final_da_campanha_já_cadastrada_é_alterada_para(String dataEsperada) throws Throwable {
        final CampanhaDTO c1 = response.getDados().stream()
                .filter(c -> c.getIdTimeCoracao().equals(1))
                .collect(Collectors.toList()).get(0);
        assertEquals(dataEsperada, c1.getDataVigencia().getDataFinal());
    }

    @Entao("^é cadastrada a nova campanha no período informado$")
    public void é_cadastrada_a_nova_campanha_no_período_informado() throws Throwable {
        final CampanhaDTO c2 = response.getDados().stream()
                .filter(c -> c.getIdTimeCoracao().equals(2))
                .collect(Collectors.toList()).get(0);
        assertEquals(dto.getDataVigencia().getDataFinal(), c2.getDataVigencia().getDataFinal());
    }

    @Entao("^a data final da primeira campanha passa a ser \"([^\"]*)\"$")
    public void a_data_final_da_primeira_campanha_passa_a_ser(String dataEsperada) throws Throwable {
        final CampanhaDTO c1 = response.getDados().stream()
                .filter(c -> c.getIdTimeCoracao().equals(1))
                .collect(Collectors.toList()).get(0);
        assertEquals(dataEsperada, c1.getDataVigencia().getDataFinal());
    }

    @Entao("^a data final da segunda campanha passa a ser \"([^\"]*)\"$")
    public void a_data_final_da_segunda_campanha_passa_a_ser(String dataEsperada) throws Throwable {
        final CampanhaDTO c1 = response.getDados().stream()
                .filter(c -> c.getIdTimeCoracao().equals(2))
                .collect(Collectors.toList()).get(0);
        assertEquals(dataEsperada, c1.getDataVigencia().getDataFinal());
    }

    @Entao("^a data final da nova campanha passa a ser \"([^\"]*)\"$")
    public void a_data_final_da_nova_campanha_passa_a_ser(String dataEsperada) throws Throwable {
        final CampanhaDTO c1 = response.getDados().stream()
                .filter(c -> c.getIdTimeCoracao().equals(3))
                .collect(Collectors.toList()).get(0);
        assertEquals(dataEsperada, c1.getDataVigencia().getDataFinal());
    }

    @Dado("^que não são informados dados na requisição$")
    public void que_não_são_informados_dados_na_requisição() throws Throwable {
        dto = null;
    }

    @Entao("^é lançada uma exceção do tipo IllegalArgumentException$")
    public void é_lançada_uma_exceção_do_tipo_IllegalArgumentException() throws Throwable {
        assertTrue(excecao != null && excecao instanceof IllegalArgumentException);
        excecao = null;
    }
}
