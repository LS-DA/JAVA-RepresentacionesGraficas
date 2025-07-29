package com.lsda.api.repositories;

import com.lsda.api.entities.Factura;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    //User findByUsername(String username);
    @Query("from Factura e where e.cufe =:cufe")
    Factura getFactura(@Param("cufe") String cufe);
    


    /*
	
	@Query("from Factura e where e.nit =:nit")
	 List<Factura> getFacturaNit(@Param("nit") String nit);
	
	 @Query("select count (e) from Factura e where e.nit =:nit")
	 int  getFacturaNitgroup(@Param("nit") String nit);
*/
}
