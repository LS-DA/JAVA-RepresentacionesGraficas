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
public class Encabezado {

    private String llaveComprobante;
    private String numeroDocumentoEmisor;
    private String tipoReceptor;
    private String tipoDocumentoReceptor;
    private String numeroDocumentoReceptor;
    private String regimenReceptor;
    private String razonSocialReceptor;
    private String nombreReceptor;
    private String segNomReceptor;
    private String apellidosReceptor;
    private String paisReceptor;
    private String departamentoReceptor;
    private String ciudadReceptor;
    private String direccionReceptor="";
    private String telefonoReceptor;
    private String telefonoMovilReceptor;
    private String mailReceptor;
    private String tipoComprobante;
    private String noResolucion;
    private String prefijo;
    private String folio;
    private String fecha;
    private String hora;
    private String jasper;
    private String moneda;
    private BigDecimal subtotal;
    private BigDecimal totalDescuentos;
    private BigDecimal totalImpuestos;
    private BigDecimal totalImpuestosRetenidos;
    private BigDecimal total;
    private String montoLetra;
    private String codigoBarrasPago;
    private String textoLibre;
    private String ncIdFact;
    private String ncCod;
    private String ncIdDoc;
    private String ncUuid;
    private String ncFecha;
    private String ndIdFact;
    private String ndCod;
    private String ndIdDoc;
    private String ndUuid;
    private String ndFecha;
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
    private int status;

    //Campos Adicionales Nuevo Modelo
    private String digitoVerificacion;
    private String obligacionesFiscalesReceptor;
    private String tributoReceptor;
    private String nombreComercialReceptor;
    private String codigoDepartamento;
    private String codigoCiudadReceptor;
    private String codigoPostalReceptor;
    private String mailReceptorContactoReceptor;
    private String nombreContactoReceptor;
    private String metodoDePago;
    private String medioDePago;
    private String fechaVencimientoFactura;
    private String terminosDePago;
    private BigDecimal baseImpuestos;
    private BigDecimal totalSinDescuentos;
    private String tasaDeCambio;
    private String incoterm;
    private String totalCargos;
    //Campos Adicionales Nuevo Modelo
    private String FechaValida;
        private String FechaValida2;


    public Encabezado() {
    }

    public String getLlaveComprobante() {
        return llaveComprobante;
    }

    public String getSegNomReceptor() {
        return segNomReceptor;
    }

    public void setSegNomReceptor(String segNomReceptor) {
        this.segNomReceptor = segNomReceptor;
    }

    public void setLlaveComprobante(String idHeader) {
        this.llaveComprobante = idHeader;
    }

    public String getNumeroDocumentoEmisor() {
        return numeroDocumentoEmisor;
    }

    public void setNumeroDocumentoEmisor(String numeroDocumentoEmisor) {
        this.numeroDocumentoEmisor = numeroDocumentoEmisor;
    }

    public String getTipoReceptor() {
        return tipoReceptor;
    }

    public void setTipoReceptor(String tipoReceptor) {
        this.tipoReceptor = tipoReceptor;
    }

    public String getTipoDocumentoReceptor() {
        return tipoDocumentoReceptor;
    }

    public void setTipoDocumentoReceptor(String tipoDocumentoReceptor) {
        this.tipoDocumentoReceptor = tipoDocumentoReceptor;
    }

    public String getNumeroDocumentoReceptor() {
        return numeroDocumentoReceptor;
    }

    public void setNumeroDocumentoReceptor(String numeroDocumentoReceptor) {
        this.numeroDocumentoReceptor = numeroDocumentoReceptor;
    }

    public String getRegimenReceptor() {
        return regimenReceptor;
    }

    public void setRegimenReceptor(String regimenReceptor) {
        this.regimenReceptor = regimenReceptor;
    }

    public String getRazonSocialReceptor() {
        return razonSocialReceptor;
    }

    public void setRazonSocialReceptor(String razonSocialReceptor) {
        this.razonSocialReceptor = razonSocialReceptor;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public String getApellidosReceptor() {
        return apellidosReceptor;
    }

    public void setApellidosReceptor(String apellidosReceptor) {
        this.apellidosReceptor = apellidosReceptor;
    }


    
    
    public String getPaisReceptor() {
        return paisReceptor;
    }

    public void setPaisReceptor(String paisReceptor) {
        this.paisReceptor = paisReceptor;
    }

    public String getDepartamentoReceptor() {
        return departamentoReceptor;
    }

    public void setDepartamentoReceptor(String departamentoReceptor) {
        this.departamentoReceptor = departamentoReceptor;
    }

    public String getCiudadReceptor() {
        return ciudadReceptor;
    }

    public void setCiudadReceptor(String ciudadReceptor) {
        this.ciudadReceptor = ciudadReceptor;
    }

    public String getDireccionReceptor() {
        return direccionReceptor;
    }

    public void setDireccionReceptor(String direccionReceptor) {
        this.direccionReceptor = direccionReceptor;
    }

    public String getTelefonoReceptor() {
        return telefonoReceptor;
    }

    public void setTelefonoReceptor(String telefonoReceptor) {
        this.telefonoReceptor = telefonoReceptor;
    }

    public String getTelefonoMovilReceptor() {
        return telefonoMovilReceptor;
    }

    public void setTelefonoMovilReceptor(String telefonoMovilReceptor) {
        this.telefonoMovilReceptor = telefonoMovilReceptor;
    }

    public String getMailReceptor() {
        return mailReceptor;
    }

    public void setMailReceptor(String mailReceptor) {
        this.mailReceptor = mailReceptor;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNoResolucion() {
        return noResolucion;
    }

    public void setNoResolucion(String noResolucion) {
        this.noResolucion = noResolucion;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getJasper() {
        return jasper;
    }

    public void setJasper(String jasper) {
        this.jasper = jasper;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(BigDecimal totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public BigDecimal getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(BigDecimal totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public BigDecimal getTotalImpuestosRetenidos() {
        return totalImpuestosRetenidos;
    }

    public void setTotalImpuestosRetenidos(BigDecimal totalImpuestosRetenidos) {
        this.totalImpuestosRetenidos = totalImpuestosRetenidos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getMontoLetra() {
        return montoLetra;
    }

    public void setMontoLetra(String montoLetra) {
        this.montoLetra = montoLetra;
    }

    public String getCodigoBarrasPago() {
        return codigoBarrasPago;
    }

    public void setCodigoBarrasPago(String codigoBarrasPago) {
        this.codigoBarrasPago = codigoBarrasPago;
    }

    public String getTextoLibre() {
        return textoLibre;
    }

    public void setTextoLibre(String textoLibre) {
        this.textoLibre = textoLibre;
    }

    public String getNcIdFact() {
        return ncIdFact;
    }

    public void setNcIdFact(String ncIdFact) {
        this.ncIdFact = ncIdFact;
    }

    public String getNcCod() {
        return ncCod;
    }

    public void setNcCod(String ncCod) {
        this.ncCod = ncCod;
    }

    public String getNcIdDoc() {
        return ncIdDoc;
    }

    public void setNcIdDoc(String ncIdDoc) {
        this.ncIdDoc = ncIdDoc;
    }

    public String getNcUuid() {
        return ncUuid;
    }

    public void setNcUuid(String ncUuid) {
        this.ncUuid = ncUuid;
    }

    public String getNcFecha() {
        return ncFecha;
    }

    public void setNcFecha(String ncFecha) {
        this.ncFecha = ncFecha;
    }

    public String getNdIdFact() {
        return ndIdFact;
    }

    public void setNdIdFact(String ndIdFact) {
        this.ndIdFact = ndIdFact;
    }

    public String getNdCod() {
        return ndCod;
    }

    public void setNdCod(String ndCod) {
        this.ndCod = ndCod;
    }

    public String getNdIdDoc() {
        return ndIdDoc;
    }

    public void setNdIdDoc(String ndIdDoc) {
        this.ndIdDoc = ndIdDoc;
    }

    public String getNdUuid() {
        return ndUuid;
    }

    public void setNdUuid(String ndUuid) {
        this.ndUuid = ndUuid;
    }

    public String getNdFecha() {
        return ndFecha;
    }

    public void setNdFecha(String ndFecha) {
        this.ndFecha = ndFecha;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //Campos Adicionales Nuevo Modelo
    public String getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(String digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getObligacionesFiscalesReceptor() {
        return obligacionesFiscalesReceptor;
    }

    public void setObligacionesFiscalesReceptor(String obligacionesFiscalesReceptor) {
        this.obligacionesFiscalesReceptor = obligacionesFiscalesReceptor;
    }

    public String getTributoReceptor() {
        return tributoReceptor;
    }

    public void setTributoReceptor(String tributoReceptor) {
        this.tributoReceptor = tributoReceptor;
    }

    public String getNombreComercialReceptor() {
        return nombreComercialReceptor;
    }

    public void setNombreComercialReceptor(String nombreComercialReceptor) {
        this.nombreComercialReceptor = nombreComercialReceptor;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoCiudadReceptor() {
        return codigoCiudadReceptor;
    }

    public void setCodigoCiudadReceptor(String codigoCiudadReceptor) {
        this.codigoCiudadReceptor = codigoCiudadReceptor;
    }

    public String getCodigoPostalReceptor() {
        return codigoPostalReceptor;
    }

    public void setCodigoPostalReceptor(String codigoPostalReceptor) {
        this.codigoPostalReceptor = codigoPostalReceptor;
    }

    public String getMailReceptorContactoReceptor() {
        return mailReceptorContactoReceptor;
    }

    public void setMailReceptorContactoReceptor(String mailReceptorContactoReceptor) {
        this.mailReceptorContactoReceptor = mailReceptorContactoReceptor;
    }

    public String getNombreContactoReceptor() {
        return nombreContactoReceptor;
    }

    public void setNombreContactoReceptor(String nombreContactoReceptor) {
        this.nombreContactoReceptor = nombreContactoReceptor;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public String getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }

    public String getFechaVencimientoFactura() {
        return fechaVencimientoFactura;
    }

    public void setFechaVencimientoFactura(String fechaVencimientoFactura) {
        this.fechaVencimientoFactura = fechaVencimientoFactura;
    }

    public String getTerminosDePago() {
        return terminosDePago;
    }

    public void setTerminosDePago(String terminosDePago) {
        this.terminosDePago = terminosDePago;
    }

    public BigDecimal getBaseImpuestos() {
        return baseImpuestos;
    }

    public void setBaseImpuestos(BigDecimal baseImpuestos) {
        this.baseImpuestos = baseImpuestos;
    }

    public BigDecimal getTotalSinDescuentos() {
        return totalSinDescuentos;
    }

    public void setTotalSinDescuentos(BigDecimal totalSinDescuentos) {
        this.totalSinDescuentos = totalSinDescuentos;
    }
    
      public String getTasaDeCambio() {
        return tasaDeCambio;
    }

    public void setTasaDeCambio(String tasaDeCambio) {
        this.tasaDeCambio = tasaDeCambio;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }
    
     public String getTotalCargos() {
        return totalCargos;
    }

    public void setTotalCargos(String totalCargos) {
        this.totalCargos = totalCargos;
    }   
    
    //Campos Adicionales Nuevo Modelo

    public String getFechaValida() {
        return FechaValida;
    }

    public void setFechaValida(String FechaValida) {
        this.FechaValida = FechaValida;
    }
 

    public String getFechaValida2() {
        return FechaValida2;
    }

    public void setFechaValida2(String FechaValida2) {
        this.FechaValida2 = FechaValida2;
    }
    
}
