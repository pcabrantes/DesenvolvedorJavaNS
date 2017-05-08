package com.pcabrantes.sociotorcedor.service.impl;

import com.pcabrantes.sociotorcedor.client.CampanhaClient;
import com.pcabrantes.sociotorcedor.client.dto.CampanhaDTO;
import com.pcabrantes.sociotorcedor.entity.SocioTorcedor;
import com.pcabrantes.sociotorcedor.repository.SocioTorcedorRepository;
import com.pcabrantes.sociotorcedor.service.SocioTorcedorService;
import com.pcabrantes.sociotorcedor.util.Mensagens;
import com.pcabrantes.sociotorcedor.util.adapter.SocioTorcedorAdapter;
import com.pcabrantes.sociotorcedor.util.dto.SocioTorcedorDTO;
import com.pcabrantes.sociotorcedor.util.exception.ServicoIndisponivelException;
import com.pcabrantes.sociotorcedor.util.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementação do Service para SocioTorcedor
 *
 * @author Paulo Cesar Abrantes
 */
@Service
public class SocioTorcedorServiceImpl implements SocioTorcedorService {

    @Autowired
    private CampanhaClient campanhaClient;

    @Autowired
    private SocioTorcedorRepository socioTorcedorRepository;

    @Override
    public MessageResponse salvar(SocioTorcedorDTO socioTorcedorDTO) throws Exception {

        MessageResponse response = new MessageResponse(HttpStatus.OK);

        final SocioTorcedor socioTorcedor = SocioTorcedorAdapter.fromSocioTorcedorDTO(socioTorcedorDTO);

        if (socioTorcedorRepository.findByEmail(socioTorcedor.getEmail()) == null) {
            socioTorcedor.setDataAtualizacao(new Date());
            socioTorcedor.setAtivo(true);
            socioTorcedorRepository.save(socioTorcedor);
        } else {
            response.setMensagem(Mensagens.TORCEDOR_JA_CADASTRADO);
        }

        try {
            response.setDados(campanhaClient.consultarCampanhas(socioTorcedor.getIdTimeCoracao()));
        } catch (ServicoIndisponivelException ex) {
            response.setMensagem(response.getMensagem() + " - " + ex.getMessage());
        }

        return response;
    }
}
