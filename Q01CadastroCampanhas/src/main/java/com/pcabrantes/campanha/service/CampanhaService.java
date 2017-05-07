package com.pcabrantes.campanha.service;

import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.response.MessageResponse;

/**
 * Classe utilizada para fazer a comunicação entre o Controller e o Repository nas operações referentes a Campanha
 *
 * @author Paulo Cesar Abrantes
 */
public interface CampanhaService {

    Integer INCLUIR = 1;
    Integer ATUALIZAR = 2;

    MessageResponse salvar(CampanhaDTO campanhaDTO, Integer operacao) throws Exception;
    MessageResponse consultar() throws Exception;
    MessageResponse consultar(Long id) throws Exception;
    MessageResponse remover(Long id) throws Exception;
}
