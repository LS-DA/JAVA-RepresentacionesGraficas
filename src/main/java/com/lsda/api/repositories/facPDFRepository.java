package com.lsda.api.repositories;

import com.lsda.api.entities.facPDF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface facPDFRepository extends JpaRepository<facPDF, Long> {
	
	@Query("from facPDF e where e.cufe =:cufe")
	facPDF getFactura(@Param("cufe") String cufe);
    //User findByUsername(String username);
}
