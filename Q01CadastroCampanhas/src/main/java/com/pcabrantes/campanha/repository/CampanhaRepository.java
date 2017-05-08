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
     * Consulta campanhas que estão na mesma vigência da campanha informada no parâmetro
     *
     * @param campanha
     * @return lista de campanhas em vigencia
     */
    @Query("select c from Campanha c where c.dataInicial <= :#{#campanha.dataFinal} and c.dataFinal >= :#{#campanha.dataInicial} and c.dataFinal >= CURRENT_DATE and c.ativo = true order by c.dataFinal asc")
    Iterable<Campanha> findCampanhasVigencia(@Param("campanha") Campanha campanha);

    /**
     * Consulta campanhas que estão vigentes em determinada data
     *
     * @return lista de campanhas vigentes em determinada data
     */
    @Query("select c from Campanha c where c.dataInicial <= CURRENT_DATE and c.dataFinal >= CURRENT_DATE and c.ativo = true")
    Iterable<Campanha> findCampanhasVigentes();


    /**
     * Consulta campanhas que estão vigentes em determinada data associadas ao time informado
     *
     * @param idTime
     * @return lista de campanhas vigentes em determinada data associadas ao time
     */
    @Query("select c from Campanha c where c.idTimeCoracao = :idTime and c.dataInicial <= CURRENT_DATE and c.dataFinal >= CURRENT_DATE and c.ativo = true")
    Iterable<Campanha> findCampanhasVigentesTime(@Param("idTime") Integer idTime);


    /**
     * Consulta uma campanha por id
     *
     * @param id
     * @return campanha ativa associada ao id informado
     */
    @Query("select c from Campanha c where c.id = :id and c.ativo = true")
    Campanha findOneAtivo(@Param("id") Long id);
}
