package com.lsda.api.repositories;

import com.lsda.api.entities.EmpresaCa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpresaJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<EmpresaCa> getAllEmpresas() {
        String sql = "SELECT nit, nombre, extra2 as passcert FROM empresa WITH (NOLOCK)";
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            EmpresaCa empresa = new EmpresaCa();
            empresa.setNit(rs.getString("nit"));
            empresa.setNombre(rs.getString("nombre"));
            empresa.setPasscert(rs.getString("passcert"));
            return empresa;
        });
    }

    public EmpresaCa getEmpresaByNit(String nit) {
        String sql = "SELECT nit, nombre, extra2 as passcert FROM empresa WITH (NOLOCK) WHERE nit = ?";
        
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{nit}, (rs, rowNum) -> {
                EmpresaCa empresa = new EmpresaCa();
                empresa.setNit(rs.getString("nit"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setPasscert(rs.getString("passcert"));
                return empresa;
            });
        } catch (Exception e) {
            return null;
        }
    }
}