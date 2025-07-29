/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsda.api.entities;

import java.math.BigDecimal;

/**
 *
 * @author TEST
 */
public class Impuesto {

    private String llaveComprobante;
    private BigDecimal valorTotal;
    private String idImpuesto;
    private String tipoImpuesto;
    private BigDecimal tasa;
    private String retenido;

    //Campos Adicionales Nuevo Modelo
    private BigDecimal baseImpuestos;
    //Campos Adicionales Nuevo Modelo

    public Impuesto() {
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public BigDecimal getTasa() {
        return tasa;
    }

    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
    }

    public String getRetenido() {
        return retenido;
    }

    public void setRetenido(String retenido) {
        this.retenido = retenido;
    }

    public String getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(String idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public String getLlaveComprobante() {
        return llaveComprobante;
    }

    public void setLlaveComprobante(String tipo) {
        this.llaveComprobante = tipo;
    }

    //Campos Adicionales Nuevo Modelo
    public BigDecimal getBaseImpuestos() {
        return baseImpuestos;
    }

    public void setBaseImpuestos(BigDecimal baseImpuestos) {
        this.baseImpuestos = baseImpuestos;
    }
    //Campos Adicionales Nuevo Modelo
}
