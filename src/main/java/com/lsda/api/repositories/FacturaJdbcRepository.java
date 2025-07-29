/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lsda.api.repositories;

import com.lsda.api.entities.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jjimenez
 */

@Repository
public class FacturaJdbcRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Factura getFacturaByCufe(String cufe) {
        String sql = "SELECT id, cufe, appres, base64doc, xml, qrdata " +
                    "FROM factura WITH (NOLOCK) " +
                    "WHERE cufe = ?";
        
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{cufe}, 
                (rs, rowNum) -> {
                    Factura factura = new Factura();
                   // factura.setId(rs.getLong("id"));
                   // factura.setCufe(rs.getString("cufe"));
                    factura.setAppres(rs.getString("appres"));
                    factura.setBase64doc(rs.getString("base64doc"));
                    // Usar directamente sin encoding
                    factura.setxml(rs.getString("xml"));
                    factura.setQrdata(rs.getString("qrdata"));
                    return factura;
                });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}