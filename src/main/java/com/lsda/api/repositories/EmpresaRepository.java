package com.lsda.api.repositories;

import com.lsda.api.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("from Empresa e where e.nit =:nit")
    Empresa getEmpresa(@Param("nit") String nit);

    @Query("select e from Empresa e")
    Empresa getEmpresaLogo();

    @Query("select e.id from Empresa e where Id = (SELECT MAX(e.id) FROM Empresa e)")
    int getLastRecord();

    @Query("select e.id from Empresa e where e.nit = :nit")
    Integer getIDEmpresaByNIT(@Param("nit") String nit);

    @Query("select e from Empresa e where e.id = :id")
    Empresa getEmpresaByID(@Param("id") int id);

}
