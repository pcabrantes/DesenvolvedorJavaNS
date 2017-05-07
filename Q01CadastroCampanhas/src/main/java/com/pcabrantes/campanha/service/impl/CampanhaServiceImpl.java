package com.pcabrantes.campanha.service.impl;

import com.pcabrantes.campanha.entity.Campanha;
import com.pcabrantes.campanha.repository.CampanhaRepository;
import com.pcabrantes.campanha.service.CampanhaService;
import com.pcabrantes.campanha.util.adapter.CampanhaAdapter;
import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.exception.RecursoNaoExistenteException;
import com.pcabrantes.campanha.util.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Paulo Cesar Abrantes
 */
@Service
public class CampanhaServiceImpl implements CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Override
    public MessageResponse salvar(CampanhaDTO campanhaDTO, Integer operacao) throws Exception {

        List<CampanhaDTO> result = new ArrayList<>();
        try {
            List<Campanha> campanhas = prepararSalvar(CampanhaAdapter.fromCampanhaDTO(campanhaDTO), operacao);
            campanhaRepository.save(campanhas);
            result = campanhas.parallelStream()
                    .map(CampanhaAdapter::toCampanhaDTO)
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            throw ex;
        }

        return new MessageResponse(HttpStatus.OK, result);
    }

    @Override
    public MessageResponse consultar() throws Exception {
        List<Campanha> campanhas = (List<Campanha>) campanhaRepository.findCampanhasVigentes(new Date());
        List<CampanhaDTO> result = campanhas.parallelStream()
                .map(CampanhaAdapter::toCampanhaDTO)
                .collect(Collectors.toList());
        return new MessageResponse(HttpStatus.OK, result);
    }

    @Override
    public MessageResponse remover(Long id) throws Exception {
        Campanha campanha = campanhaRepository.findOne(id);

        if (campanha == null) {
            throw new RecursoNaoExistenteException();
        }

        campanha.setDataAtualizacao(new Date());
        campanha.setAtivo(false);

        campanhaRepository.save(campanha);

        return new MessageResponse(HttpStatus.OK);
    }

    private List<Campanha> prepararSalvar(Campanha campanha, Integer operacao) throws RecursoNaoExistenteException {

        if (campanha == null || StringUtils.isEmpty(campanha.getNome()) || StringUtils.isEmpty(campanha.getIdTimeCoracao())
                || campanha.getDataInicial() == null || campanha.getDataFinal() == null
                || campanha.getDataInicial().after(campanha.getDataFinal())
                || (operacao == CampanhaService.ATUALIZAR && (campanha.getId() == null || campanha.getId() == 0L))) {
            throw new IllegalArgumentException();
        }

        campanha.setAtivo(true);
        campanha.setDataAtualizacao(new Date());

        List<Campanha> campanhas = (List<Campanha>) campanhaRepository.findCampanhasVigencia(campanha);

        if (operacao.equals(CampanhaService.ATUALIZAR)) {

            if (campanhas.parallelStream()
                    .filter(c->c.getId().equals(campanha.getId()))
                    .collect(Collectors.toList()).isEmpty()) {
                throw new RecursoNaoExistenteException();
            }

            campanhas = campanhas.parallelStream()
                    .filter(c->!c.getId().equals(campanha.getId()))
                    .collect(Collectors.toList());
        }

        if (campanhas != null && !campanhas.isEmpty()) {

            atualizarDataFinal(campanhas, null);
            List<Campanha> atualizar = campanhas.stream()
                    .filter(c -> c.getDataFinal().compareTo(campanha.getDataFinal()) >= 0)
                    .collect(Collectors.toList());

            campanhas.removeAll(atualizar);
            atualizarDataFinal(atualizar, campanha);
            campanhas.addAll(atualizar);
        } else {
            campanhas.add(campanha);
        }

        return campanhas;
    }

    private void atualizarDataFinal(List<Campanha> campanhas, Campanha novaCampanha) {

        final List<Campanha> percorridas = new ArrayList<>();
        final Calendar cal = Calendar.getInstance();

        if (novaCampanha == null) {
            campanhas.forEach(c -> {
                cal.setTime(c.getDataFinal());
                cal.add(Calendar.DAY_OF_MONTH, 1);

                if (!percorridas.isEmpty() && percorridas.get(percorridas.size() - 1).getDataFinal().equals(cal.getTime())) {
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                }

                c.setDataFinal(cal.getTime());
                percorridas.add(c);
            });
        } else {
            cal.setTime(novaCampanha.getDataFinal());
            campanhas.forEach(c -> {
                if (c.getDataFinal().equals(cal.getTime())) {
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                } else {
                    return;
                }
            });
            novaCampanha.setDataFinal(cal.getTime());
            campanhas.add(novaCampanha);
        }
    }
}
