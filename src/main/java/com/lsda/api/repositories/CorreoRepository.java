package com.lsda.api.repositories;

import com.lsda.api.entities.Correo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CorreoRepository extends JpaRepository<Correo, Long> {

	@Query("from Correo c where c.idEmpresa = :idEmpresa")
	 Correo getCorreo(@Param("idEmpresa") Integer idEMpresa);
}
