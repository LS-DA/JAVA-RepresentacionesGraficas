/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lsda.api.entities;

import java.math.BigDecimal;

/**
 *
 * @author jljg
 */
public class DescuentoLinea {
    
    private String Descripcion;
    private BigDecimal ValorDescuento;

    public DescuentoLinea() {
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public BigDecimal getValorDescuento() {
        return ValorDescuento;
    }

    public void setValorDescuento(BigDecimal ValorDescuento) {
        this.ValorDescuento = ValorDescuento;
    }
    
    
    
    
}
