package com.pcabrantes.campanha.repository;

import com.pcabrantes.campanha.entity.Campanha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
}
