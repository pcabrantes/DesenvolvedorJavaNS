package com.pcabrantes.sociotorcedor.util.adapter;

import com.pcabrantes.sociotorcedor.entity.SocioTorcedor;
import com.pcabrantes.sociotorcedor.util.dto.SocioTorcedorDTO;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe utilizada para fazer conversões entre SocioTorcedor e SocioTorcedorDTO
 *
 * @author Paulo Cesar Abrantes
 */
public class SocioTorcedorAdapter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Converte de SocioTorcedorDTO para SocioTorcedor
     *
     * @param socioTorcedorDTO
     * @return
     * @throws ParseException
     */
    public static SocioTorcedor fromSocioTorcedorDTO(SocioTorcedorDTO socioTorcedorDTO) throws ParseException {

        if (socioTorcedorDTO == null || StringUtils.isEmpty(socioTorcedorDTO.getDataNascimento())
                || StringUtils.isEmpty(socioTorcedorDTO.getEmail())
                || StringUtils.isEmpty(socioTorcedorDTO.getIdTimeCoracao())
                || StringUtils.isEmpty(socioTorcedorDTO.getNome())) {
            throw new IllegalArgumentException();
        }

        final SocioTorcedor socioTorcedor = new SocioTorcedor();
        socioTorcedor.setNome(socioTorcedorDTO.getNome());
        socioTorcedor.setDataNascimento(DATE_FORMAT.parse(socioTorcedorDTO.getDataNascimento()));
        socioTorcedor.setEmail(socioTorcedorDTO.getEmail());
        socioTorcedor.setIdTimeCoracao(Integer.parseInt(socioTorcedorDTO.getIdTimeCoracao()));

        return socioTorcedor;
    }

    /**
     * Converte de SocioTorcedor para SocioTorcedorDTO
     *
     * @param socioTorcedor
     * @return
     */
    public static SocioTorcedorDTO toSocioTorcedorDTO(SocioTorcedor socioTorcedor) {

        final SocioTorcedorDTO socioTorcedorDTO = new SocioTorcedorDTO();
        socioTorcedorDTO.setNome(socioTorcedor.getNome());
        socioTorcedorDTO.setDataNascimento(DATE_FORMAT.format(socioTorcedor.getDataNascimento()));
        socioTorcedorDTO.setEmail(socioTorcedor.getEmail());
        socioTorcedorDTO.setIdTimeCoracao(String.valueOf(socioTorcedor.getIdTimeCoracao()));

        return socioTorcedorDTO;
    }

}
