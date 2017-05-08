package com.pcabrantes.sociotorcedor.controller;

import com.pcabrantes.sociotorcedor.service.SocioTorcedorService;
import com.pcabrantes.sociotorcedor.util.dto.SocioTorcedorDTO;
import com.pcabrantes.sociotorcedor.util.response.MessageResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Controller utilizado para expor os serviços de Sócio Torcedor
 *
 * @author Paulo Cesar Abrantes
 */
@RestController
public class SocioTorcedorController {

    private final Logger logger = getLogger(this.getClass());

    @Autowired
    private SocioTorcedorService socioTorcedorService;

    /**
     * Método utilizado para cadastrar um novo Sócio Torcedor
     *
     * @param socioTorcedorDTO Objeto SocioTorcedorDTO no formato json
     * @return Um json informando o sucesso da operação
     *
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/sociotorcedor/cadastrar")
    public MessageResponse cadastrar(@RequestBody SocioTorcedorDTO socioTorcedorDTO) throws Exception {
        logger.info("Serviço iniciado: POST /sociotorcedor/cadastrar");
        logger.info("RequestBody: " + socioTorcedorDTO);
        return socioTorcedorService.salvar(socioTorcedorDTO);
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
