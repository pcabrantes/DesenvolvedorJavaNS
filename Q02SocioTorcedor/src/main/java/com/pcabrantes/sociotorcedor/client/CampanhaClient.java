package com.pcabrantes.sociotorcedor.client;

import com.pcabrantes.sociotorcedor.client.dto.CampanhaDTO;
import com.pcabrantes.sociotorcedor.util.exception.ServicoIndisponivelException;
import com.pcabrantes.sociotorcedor.util.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Classe responsável por acessar os serviços providos pelo sistema de cadastro de campanhas
 *
 * @author Paulo Cesar Abrantes
 */
@Component
public class CampanhaClient {

    private Properties propriedades;
    private RestTemplate restTemplate;

    private static String CONSULTAR_CAMPANHAS_TIME;

    public CampanhaClient() {
        try {
            propriedades = new Properties();
            restTemplate = new RestTemplate();
            final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
            propriedades.load(inputStream);

            CONSULTAR_CAMPANHAS_TIME = propriedades.getProperty("campanha.client.url") + "campanhas/time/";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Consulta o sistema de campanhas para obter as campanhas vigentes associadas a determinado time
     *
     * @param idTimeCoracao
     * @return Lista de campanhas associadas ao time
     */
    public List<CampanhaDTO> consultarCampanhas(Integer idTimeCoracao) throws Exception {

        MessageResponse response = null;

        try {
            response =
                    restTemplate.getForObject(CONSULTAR_CAMPANHAS_TIME + idTimeCoracao, MessageResponse.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServicoIndisponivelException();
        }

        return response == null || !response.getCodigo().equals(HttpStatus.OK.value()) ? null : response.getDados();
    }

}
