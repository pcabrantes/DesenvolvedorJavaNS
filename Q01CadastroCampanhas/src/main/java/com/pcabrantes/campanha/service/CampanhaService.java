package com.pcabrantes.campanha.service;

import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.response.MessageResponse;

/**
 * Classe utilizada para fazer a comunicação entre o Controller e o Repository nas operações referentes a Campanha
 *
 * @author Paulo Cesar Abrantes
 */
public interface CampanhaService {

    MessageResponse salvar(CampanhaDTO campanhaDTO) throws Exception;
    MessageResponse consultar() throws Exception;
}
