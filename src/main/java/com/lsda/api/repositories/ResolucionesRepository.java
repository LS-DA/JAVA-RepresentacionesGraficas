package com.lsda.api.repositories;

import com.lsda.api.entities.Resoluciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResolucionesRepository extends JpaRepository <Resoluciones, Long> {
	
	@Query("from Resoluciones r where r.noresolucion = :nores and r.idempresa = :idempresa and r.prefijo=:prefijo")
	Resoluciones getResolucion(@Param("nores") String noRes, @Param("idempresa") int idempresa,@Param("prefijo") String prefijo);
}
