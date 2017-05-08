package com.pcabrantes.sociotorcedor.repository;

import com.pcabrantes.sociotorcedor.entity.SocioTorcedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository utilizado para comunicação com o BD para SocioTorcedor
 *
 * @author Paulo Cesar Abrantes
 */
public interface SocioTorcedorRepository extends CrudRepository<SocioTorcedor,Long>{


    /**
     * Consulta um Sócio Torcedor a partir do email
     *
     * @param email
     * @return
     */
    @Query("select s from SocioTorcedor s where s.email = ? and s.ativo = true")
    SocioTorcedor findByEmail(String email);

}
