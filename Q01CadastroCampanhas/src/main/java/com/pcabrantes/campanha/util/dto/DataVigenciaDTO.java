package com.pcabrantes.campanha.util.dto;

/**
 * Classe utilizada para representar o período de vigência de uma campanha
 *
 * @author Paulo Cesar Abrantes
 */
public class DataVigenciaDTO {

    private String dataInicial;
    private String dataFinal;

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
}
