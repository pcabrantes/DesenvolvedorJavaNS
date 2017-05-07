package com.pcabrantes.campanha.util.adapter;

import com.pcabrantes.campanha.entity.Campanha;
import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.dto.DataVigenciaDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Adapter utilizado para fazer conversões entre os tipos Campanha e CampanhaDTO
 *
 * @author Paulo Cesar Abrantes
 */
public class CampanhaAdapter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static Campanha fromCampanhaDTO(CampanhaDTO campanhaDTO) throws ParseException {

        if (campanhaDTO == null || (campanhaDTO.getId() == null && campanhaDTO.getIdTimeCoracao() == null)
                && campanhaDTO.getDataVigencia() == null) {
            throw new IllegalArgumentException();
        }

        final Campanha campanha = new Campanha();
        campanha.setId(campanhaDTO.getId());
        campanha.setNome(campanhaDTO.getNome());
        campanha.setIdTimeCoracao(campanhaDTO.getIdTimeCoracao());

        final DataVigenciaDTO dataVigencia = campanhaDTO.getDataVigencia();

        if (dataVigencia != null) {
            campanha.setDataInicial(DATE_FORMAT.parse(dataVigencia.getDataInicial()));
            campanha.setDataFinal(DATE_FORMAT.parse(dataVigencia.getDataFinal()));

        }

        return campanha;
    }

    public static CampanhaDTO toCampanhaDTO(Campanha campanha) {

        final CampanhaDTO dto = new CampanhaDTO();
        dto.setId(campanha.getId());
        dto.setNome(campanha.getNome());
        dto.setIdTimeCoracao(campanha.getIdTimeCoracao());

        final DataVigenciaDTO dataDTO = new DataVigenciaDTO();
        dataDTO.setDataInicial(DATE_FORMAT.format(campanha.getDataInicial()));
        dataDTO.setDataFinal(DATE_FORMAT.format(campanha.getDataFinal()));

        dto.setDataVigencia(dataDTO);

        return dto;
    }
}
