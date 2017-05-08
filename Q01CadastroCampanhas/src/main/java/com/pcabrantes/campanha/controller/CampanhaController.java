package com.pcabrantes.campanha.controller;

import com.pcabrantes.campanha.service.CampanhaService;
import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.exception.RecursoNaoExistenteException;
import com.pcabrantes.campanha.util.response.MessageResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Classe responsável por expor os serviços REST
 *
 * @author Paulo Cesar Abrantes
 */
@RestController
public class CampanhaController {

    private final Logger logger = getLogger(this.getClass());

    @Autowired
    private CampanhaService campanhaService;

    /**
     * Método utilizado para salvar uma nova campanha
     *
     * @param campanha Objeto Campanha no formato json
     * @return Um json informando o sucesso da operação
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/campanha/cadastrar")
    public MessageResponse cadastrar(@RequestBody CampanhaDTO campanha) throws Exception {
        logger.info("Serviço iniciado: POST /campanha");
        logger.info("RequestBody: " + campanha);
        return campanhaService.salvar(campanha, CampanhaService.INCLUIR);
    }

    /**
     * Método utilizado para salvar uma nova campanha
     *
     * @param campanha Objeto Campanha no formato json
     * @return Um json informando o sucesso da operação
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/campanha/atualizar")
    public MessageResponse atualizar(@RequestBody CampanhaDTO campanha) throws Exception {
        logger.info("Serviço iniciado: PUT /campanha");
        logger.info("RequestBody: " + campanha);
        return campanhaService.salvar(campanha, CampanhaService.ATUALIZAR);
    }

    /**
     * Método utilizado para consultar campanhas em vigência
     *
     * @return Um json contendo os dados da consulta
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/campanhas")
    public MessageResponse consultar() throws Exception {
        logger.info("Serviço iniciado: GET /campanha");
        return campanhaService.consultar();
    }

    /**
     * Método utilizado para consultar campanhas em vigência associadas a um time
     *
     * @return Um json contendo os dados da consulta
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/campanhas/time/{id}")
    public MessageResponse consultarPorTime(@PathVariable(value = "id") Integer id) throws Exception {
        logger.info("Serviço iniciado: GET /campanha");
        return campanhaService.consultarPorTime(id);
    }

    /**
     * Método utilizado para consultar uma campanha através de seu id
     *
     * @return Um json contendo os dados da consulta
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/campanha/{id}")
    public MessageResponse consultar(@PathVariable(value = "id") Long id) throws Exception {
        logger.info("Serviço iniciado: GET /campanha/{" + id + "}");
        return campanhaService.consultar(id);
    }

    /**
     * Método utilizado para remover uma campanha através de seu id
     *
     * @return Um json informando o sucesso da operação
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/campanha/{id}")
    public MessageResponse remover(@PathVariable(value = "id") Long id) throws Exception {
        logger.info("Serviço iniciado: DELETE /campanha/{" + id + "}");
        return campanhaService.remover(id);
    }

    /**
     * Método utilizado para tratar exceções quando o json informado não estiver no formato correto
     *
     * @param ex
     * @return Um json no seguinte formato:
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handlerBadRequest(IllegalArgumentException ex) {
        logger.error(ex.getMessage(), ex);
        return new MessageResponse(HttpStatus.BAD_REQUEST);
    }

    /**
     * Método utilizado para tratar exceções quando o json informado não estiver no formato correto
     *
     * @param ex
     * @return Um json no seguinte formato:
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handlerBadRequest(ParseException ex) {
        logger.error(ex.getMessage(), ex);
        return new MessageResponse(HttpStatus.BAD_REQUEST);
    }

    /**
     * Método utilizado para tratar exceções ao tentar acessar recursos que não existem
     *
     * @param ex
     * @return Um json no seguinte formato:
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponse handlerNotFound(RecursoNaoExistenteException ex) {
        logger.error(ex.getMessage(), ex);
        return new MessageResponse(HttpStatus.NOT_FOUND);
    }

    /**
     * Método utilizado para tratar exceções quando acontecer um erro inesperado
     *
     * @param ex
     * @return Um json no seguinte formato:
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MessageResponse handlerInternalServerError(NullPointerException ex) {
        logger.error(ex.getMessage(), ex);
        return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
