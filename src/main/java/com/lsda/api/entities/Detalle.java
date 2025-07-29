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
public class Detalle {

    private String llaveComprobante;
    private String idDetalle;
    private String cantidad;
    private String descripcion;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private String informacionAdicional;
    private String extra1;
    private String extra2;
    private String extra3;
    private String extra4;
    private String extra5;
    private String extra6;
    private String extra7;
    private String extra8;
    private String extra9;
    private String extra10;
    private String extra11;
    private String extra12;
    private String extra13;
    private String extra14;
    private String extra15;
    private String extra16;
    private String extra17;
    private String extra18;

    //Campos Adicionales Nuevo Modelo
    private String unidadDeMedida;
    private BigDecimal impuestoLinea;
    private String tasa;
    private String tipoImpuesto;
    private BigDecimal baseImpuesto;
    private String subPartidaArancelaria;
    private String identificacionProductos;
    private BigDecimal importedescuento;
    private String descripciondescuento;
    
    //Campos Adicionales Nuevo Modelo

    public Detalle() {

    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }

    public String getExtra5() {
        return extra5;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5;
    }

    public String getExtra6() {
        return extra6;
    }

    public void setExtra6(String extra6) {
        this.extra6 = extra6;
    }

    public String getExtra7() {
        return extra7;
    }

    public void setExtra7(String extra7) {
        this.extra7 = extra7;
    }

    public String getExtra8() {
        return extra8;
    }

    public void setExtra8(String extra8) {
        this.extra8 = extra8;
    }

    public String getExtra9() {
        return extra9;
    }

    public void setExtra9(String extra9) {
        this.extra9 = extra9;
    }

    public String getExtra10() {
        return extra10;
    }

    public void setExtra10(String extra10) {
        this.extra10 = extra10;
    }

    public String getExtra11() {
        return extra11;
    }

    public void setExtra11(String extra11) {
        this.extra11 = extra11;
    }

    public String getExtra12() {
        return extra12;
    }

    public void setExtra12(String extra12) {
        this.extra12 = extra12;
    }

    public String getExtra13() {
        return extra13;
    }

    public void setExtra13(String extra13) {
        this.extra13 = extra13;
    }

    public String getExtra14() {
        return extra14;
    }

    public void setExtra14(String extra14) {
        this.extra14 = extra14;
    }

    public String getExtra15() {
        return extra15;
    }

    public void setExtra15(String extra15) {
        this.extra15 = extra15;
    }

    public String getExtra16() {
        return extra16;
    }

    public void setExtra16(String extra16) {
        this.extra16 = extra16;
    }

    public String getExtra17() {
        return extra17;
    }

    public void setExtra17(String extra17) {
        this.extra17 = extra17;
    }

    public String getExtra18() {
        return extra18;
    }

    public void setExtra18(String extra18) {
        this.extra18 = extra18;
    }

    public String getLlaveComprobante() {
        return llaveComprobante;
    }

    public void setLlaveComprobante(String tipo) {
        this.llaveComprobante = tipo;
    }

    //Campos Adicionales Nuevo Modelo
    
    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public BigDecimal getImpuestoLinea() {
        return impuestoLinea;
    }

    public void setImpuestoLinea(BigDecimal impuestoLinea) {
        this.impuestoLinea = impuestoLinea;
    }

    public String getTasa() {
        return tasa;
    }

    public void setTasa(String tasa) {
        this.tasa = tasa;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public BigDecimal getBaseImpuesto() {
        return baseImpuesto;
    }

    public void setBaseImpuesto(BigDecimal baseImpuesto) {
        this.baseImpuesto = baseImpuesto;
    }

    public String getSubPartidaArancelaria() {
        return subPartidaArancelaria;
    }

    public void setSubPartidaArancelaria(String subPartidaArancelaria) {
        this.subPartidaArancelaria = subPartidaArancelaria;
    }

    public String getIdentificacionProductos() {
        return identificacionProductos;
    }

    public void setIdentificacionProductos(String IdentificacionProductos) {
        this.identificacionProductos = IdentificacionProductos;
    }
    //Campos Adicionales Nuevo Modelo

    public BigDecimal getImportedescuento() {
        return importedescuento;
    }

    public void setImportedescuento(BigDecimal importedescuento) {
        this.importedescuento = importedescuento;
    }

    public String getDescripciondescuento() {
        return descripciondescuento;
    }

    public void setDescripciondescuento(String descripciondescuento) {
        this.descripciondescuento = descripciondescuento;
    }
    
    
}
