package com.pcabrantes.sociotorcedor.service;

import com.pcabrantes.sociotorcedor.util.dto.SocioTorcedorDTO;
import com.pcabrantes.sociotorcedor.util.response.MessageResponse;

/**
 * Service utilizado para fazer a comunicação entre o Controller e o Repository do SocioTorcedor
 *
 * @author Paulo Cesar Abrantes
 */
public interface SocioTorcedorService {

    /**
     * Salva um SocioTorcedor na base de dados
     *
     * @param socioTorcedorDTO
     * @return objeto com informações sobre o sucesso da operação e campanhas associadas ao time informado
     */
    MessageResponse salvar(SocioTorcedorDTO socioTorcedorDTO) throws Exception;
}
