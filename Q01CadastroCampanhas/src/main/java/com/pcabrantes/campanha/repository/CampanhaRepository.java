package com.pcabrantes.campanha.repository;

import com.pcabrantes.campanha.entity.Campanha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Interface responsável por executar operações no BD referentes a Campanha
 *
 * @author Paulo Cesar Abrantes
 */
public interface CampanhaRepository extends CrudRepository<Campanha,Long> {

    /**
     * Retorna campanhas que estão na mesma vigência da campanha inforada no parâmetro
     *
     * @param campanha
     * @return
     */
    @Query("select c from Campanha c where c.dataInicial <= :#{#campanha.dataFinal} and c.dataFinal >= :#{#campanha.dataInicial} and c.ativo = true order by c.dataFinal asc")
    Iterable<Campanha> findCampanhasVigencia(@Param("campanha") Campanha campanha);

    @Query("select c from Campanha c where c.dataInicial <= :dataAtual and c.dataFinal >= :dataAtual and c.ativo = true")
    Iterable<Campanha> findCampanhasVigentes(@Param("dataAtual") Date dataAtual);

    @Query("select c from Campanha c where c.id = ? and c.ativo = true")
    Campanha findOneAtivo(Long id);
}
