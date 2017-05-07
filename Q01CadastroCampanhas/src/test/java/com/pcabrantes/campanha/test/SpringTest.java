package com.pcabrantes.campanha.test;

import com.pcabrantes.campanha.Application;
import com.pcabrantes.campanha.util.dto.CampanhaDTO;
import com.pcabrantes.campanha.util.dto.DataVigenciaDTO;
import org.springframework.test.context.ContextConfiguration;

/**
 * Classe Genérica de Testes
 *
 * @author Paulo Cesar Abrantes
 */
@ContextConfiguration(classes = Application.class)
public class SpringTest {

    public CampanhaDTO criarDTO(String nome, int idTime, String dtInicial, String dtFinal) {

        final CampanhaDTO dto = new CampanhaDTO();
        dto.setNome(nome);
        dto.setIdTimeCoracao(idTime);

        DataVigenciaDTO data = new DataVigenciaDTO();
        data.setDataInicial(dtInicial);
        data.setDataFinal(dtFinal);

        dto.setDataVigencia(data);

        return dto;
    }
}
