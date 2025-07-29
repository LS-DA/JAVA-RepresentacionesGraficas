/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lsda.api.repositories;

import com.lsda.api.entities.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jjimenez
 */
@Service
public class FacturaService {
    
    @Autowired
    private FacturaJdbcRepository facturaJdbcRepository;
    
    @Autowired
    private FacturaRepository facturaRepository; // Keep for other operations
    
    // Usa JDBC para consultas críticas de rendimiento
    public Factura getFacturaOptimizada(String cufe) {
        return facturaJdbcRepository.getFacturaByCufe(cufe);
    }
    
    // Usa JPA para operaciones CRUD normales
    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }
}