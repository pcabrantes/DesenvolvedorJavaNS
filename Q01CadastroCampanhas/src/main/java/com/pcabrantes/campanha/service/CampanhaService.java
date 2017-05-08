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

    /**
     * Método responsável por incluir/atualizar uma campanha
     *
     * @param campanhaDTO
     * @param operacao
     * @return
     * @throws Exception
     */
    MessageResponse salvar(CampanhaDTO campanhaDTO, Integer operacao) throws Exception;

    /**
     * Método responsável por consultar as campanhas vigentes
     *
     * @return
     * @throws Exception
     */
    MessageResponse consultar() throws Exception;

    /**
     * Método responsável po consultar uma campanha com base em seu id
     *
     * @param id
     * @return
     * @throws Exception
     */
    MessageResponse consultar(Long id) throws Exception;

    /**
     * Método responsável por consultar as campanhas associadas a um time
     *
     * @param id
     * @return
     * @throws Exception
     */
    MessageResponse consultarPorTime(Integer id) throws Exception;
    MessageResponse remover(Long id) throws Exception;
}
