package com.pcabrantes.campanha.controller;

import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.response.MessageResponse;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Classe responsável por expor os serviços REST
 *
 * @author Paulo Cesar Abrantes
 */
@RestController(value = "/campanha")
public class CampanhaController {

    private final Logger logger = getLogger(this.getClass());

    /**
     * Método utilizado para cadastrar uma nova campanha
     *
     * @param campanha Objeto Campanha no formato json
     * @return Um json informando o sucesso da operação
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public MessageResponse cadastrar(@RequestBody CampanhaDTO campanha) throws Exception {
        logger.info("Serviço iniciado: POST /campanha");
        logger.info("RequestBody: " + campanha);
        return null;
    }

    /**
     * Método utilizado para cadastrar uma nova campanha
     *
     * @param campanha Objeto Campanha no formato json
     * @return Um json informando o sucesso da operação
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.PUT)
    public MessageResponse atualizar(@RequestBody CampanhaDTO campanha) throws Exception {
        logger.info("Serviço iniciado: PUT /campanha");
        logger.info("RequestBody: " + campanha);
        return null;
    }

    /**
     * Método utilizado para consultar campanhas em vigência
     *
     * @return Um json contendo os dados da consulta
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public MessageResponse consultar() throws Exception {
        logger.info("Serviço iniciado: GET /campanha");
        return null;
    }

    /**
     * Método utilizado para consultar uma campanha através de seu id
     *
     * @return Um json contendo os dados da consulta
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public MessageResponse consultar(@PathVariable(value = "id") Long id) throws Exception {
        logger.info("Serviço iniciado: GET /campanha/{" + id + "}");
        return null;
    }

    /**
     * Método utilizado para remover uma campanha através de seu id
     *
     * @return Um json informando o sucesso da operação
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public MessageResponse remover(@PathVariable(value = "id") Long id) throws Exception {
        logger.info("Serviço iniciado: DELETE /campanha/{" + id + "}");
        return null;
    }
}
