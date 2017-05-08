package com.pcabrantes.sociotorcedor.test.service;

import com.pcabrantes.sociotorcedor.entity.SocioTorcedor;
import com.pcabrantes.sociotorcedor.service.SocioTorcedorService;
import com.pcabrantes.sociotorcedor.test.SpringTest;
import com.pcabrantes.sociotorcedor.util.Mensagens;
import com.pcabrantes.sociotorcedor.util.dto.SocioTorcedorDTO;
import com.pcabrantes.sociotorcedor.util.response.MessageResponse;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Steps para cenários de teste de cadastro de torcedores
 *
 * @author Paulo Cesar Abrantes
 */
public class CadastrarTorcedorTest extends SpringTest {

    @Autowired
    private SocioTorcedorService socioTorcedorService;

    private MessageResponse response = null;
    private SocioTorcedorDTO socioTorcedorDTO;
    private Exception excecao = null;

    @Dado("^que foi informado um cliente com nome \"([^\"]*)\", email \"([^\"]*)\", nascimento \"([^\"]*)\" e time (\\d+)$")
    public void que_foi_informado_um_cliente_com_nome_email_nascimento_e_time(String nome, String email, String nascimento, int time) throws Throwable {
        socioTorcedorDTO = new SocioTorcedorDTO();
        socioTorcedorDTO.setNome(nome);
        socioTorcedorDTO.setEmail(email);
        socioTorcedorDTO.setDataNascimento(nascimento);
        socioTorcedorDTO.setIdTimeCoracao(String.valueOf(time));
    }

    @Quando("^invocar o serviço de cadastro de sócio torcedor$")
    public void invocar_o_serviço_de_cadastro_de_sócio_torcedor() throws Throwable {
        try {
            response = socioTorcedorService.salvar(socioTorcedorDTO);
        } catch (Exception ex) {
            excecao = ex;
        }

    }

    @Entao("^efetuar o cadastro com os dados informados$")
    public void efetuar_o_cadastro_com_os_dados_informados() throws Throwable {
        assertEquals(new Integer(200), response.getCodigo());
    }

    @Entao("^informar que o cadastro já foi efetuado$")
    public void informar_que_o_cadastro_já_foi_efetuado() throws Throwable {
        assertTrue(response.getMensagem().contains(Mensagens.TORCEDOR_JA_CADASTRADO));
    }

    @Dado("^que foram informados dados incompletos do torcedor$")
    public void que_foram_informados_dados_incompletos_do_torcedor() throws Throwable {
        socioTorcedorDTO = new SocioTorcedorDTO();
    }

    @Entao("^lançar uma excecao do tipo IllegalArgumentException$")
    public void lançar_uma_excecao_do_tipo_IllegalArgumentException() throws Throwable {
        assertTrue(excecao instanceof IllegalArgumentException);
    }

    @Dado("^que o torcedor informou uma data de nascimento inválida$")
    public void que_o_torcedor_informou_uma_data_de_nascimento_inválida() throws Throwable {
        socioTorcedorDTO = new SocioTorcedorDTO();
        socioTorcedorDTO.setNome("nome");
        socioTorcedorDTO.setEmail("email");
        socioTorcedorDTO.setDataNascimento("nascimento");
        socioTorcedorDTO.setIdTimeCoracao(String.valueOf("1"));
    }

    @Entao("^lancar uma excecao do tipo ParseException$")
    public void lancar_uma_excecao_do_tipo_ParseException() throws Throwable {
        assertTrue(excecao instanceof ParseException);
    }

}
