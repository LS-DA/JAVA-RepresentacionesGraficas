/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsda.plantilla;

import com.lsda.api.PDF.GeneracionPDF;
import com.lsda.api.entities.Detalle;
import com.lsda.api.entities.DetalleNegocio;
import com.lsda.api.entities.Impuesto;
import static com.lsda.plantilla.CreacionRepresentacion.RUTA_BASE_CONFIG;
import static com.lsda.plantilla.CreacionRepresentacion.RUTA_BASE_IMAGENES;
import static com.lsda.plantilla.CreacionRepresentacion.RUTA_BASE_PDF;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author TEST
 */
public class Orso_Aldelo extends CreacionRepresentacion {

    @Override
    public void inicializarVariables() {
        System.out.println("Inicializando Variables..\n");
        hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        jasperFile = new File(RUTA_BASE_CONFIG + encabezado.getNumeroDocumentoEmisor() + "/" + nombreJasper);
        rutaImagenQr = RUTA_BASE_IMAGENES + encabezado.getNumeroDocumentoEmisor() + "/qr_code.png";
        rutaLogo = RUTA_BASE_IMAGENES + encabezado.getNumeroDocumentoEmisor() + "/logo.png";
        //rutaLogo1 = RUTA_BASE_IMAGENES + encabezado.getNumeroDocumentoEmisor() + "/logo1.png";
        pdfFile = new File(RUTA_BASE_PDF + encabezado.getNumeroDocumentoEmisor() + "/" + nombrarDocumento(encabezado) + ".pdf");

        try {
            ubicacionLogo = new FileInputStream(new File(rutaLogo));
            //ubicacionLogo1 = new FileInputStream(new File(rutaLogo1));
            ubicacionQr = new FileInputStream(new File(rutaImagenQr));
        } catch (FileNotFoundException ex) {
            cargarError("Error Fichero Logo Vansolix: " + ex.getMessage());
            java.util.logging.Logger.getLogger(GeneracionPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            reporte = (JasperReport) JRLoader.loadObject(jasperFile);
        } catch (JRException ex) {
            System.out.println("Error Jasper Vansolix: " + ex.getMessage());
            cargarError("Error Jasper Vansolix: " + ex.getMessage());
            ex.printStackTrace();
        }
        parametros = new HashMap<>();
        conceptosAReportar = new ArrayList<>();
    }

    @Override
    public void detallesFactura() {
        System.out.println("Detalles factura..\n");
        parametros.put("folio", encabezado.getFolio());
        parametros.put("prefijo", encabezado.getPrefijo());
        if ("".equals(encabezado.getPrefijo())) {
            parametros.put("nroFactura", encabezado.getFolio());
        } else {
            parametros.put("nroFactura", encabezado.getPrefijo() + "-" + encabezado.getFolio());
        }
        parametros.put("fechaFactura", encabezado.getFecha());
        parametros.put("montoLetra", encabezado.getMontoLetra());
        parametros.put("extra1", encabezado.getExtra1());//Punto de Venta
        parametros.put("extra2", encabezado.getExtra2());//Asesor Comercial
        parametros.put("extra3", encabezado.getExtra3());//Cotizacion
        parametros.put("extra4", encabezado.getExtra4());//Orden de compra/contrato
        parametros.put("extra5", encabezado.getExtra5());//Pedido
        parametros.put("extra6", encabezado.getExtra6());//Fecha Vencimiento
        parametros.put("extra7", encabezado.getExtra7());//Direccion radicación
        parametros.put("extra8", encabezado.getExtra8());//Datos bancarios

        parametros.put("extra9", encabezado.getExtra9());//Encabezado
        parametros.put("extra10", encabezado.getExtra10());//Encabezado
        parametros.put("extra11", encabezado.getExtra11());//Encabezado
        parametros.put("fechaVencimiento", encabezado.getFechaVencimientoFactura());
        parametros.put("terminosPago", encabezado.getTerminosDePago());
        parametros.put("medioPago", encabezado.getMedioDePago());
        parametros.put("metodoPago", encabezado.getMetodoDePago());

        parametros.put("tasaCambio", encabezado.getTasaDeCambio());
        parametros.put("incoterm", encabezado.getIncoterm());
        parametros.put("moneda", encabezado.getMoneda());

        // parametros.put("iva", convertirBigDecimalAformatoDecimal(encabezado.getTotalImpuestos().doubleValue()));
        parametros.put("subtotal", convertirBigDecimalAformatoDecimal(encabezado.getSubtotal().doubleValue()));
        parametros.put("total", convertirBigDecimalAformatoDecimal(encabezado.getTotal().doubleValue()));

        parametros.put("cufe", cufe);
        parametros.put("observaciones", encabezado.getTextoLibre());
        parametros.put("sello", extraerFirmaDeXmlSellado(this.xmlSellado));
        parametros.put("descuento", convertirBigDecimalAformatoDecimal(encabezado.getTotalDescuentos().doubleValue()));

        //parametros.put("extra6", encabezado.getExtra6());//Vendedor
        parametros.put("fechaValidacionDIAN", encabezado.getFechaValida());
    }

    @Override
    public void agregarInformacionEmisor() {
        System.out.println("Informacion Emisor..\n");

        System.out.println("Resolucion: " + resolucion.getNoresolucion() + " " + resolucion.getFechaexpedicion());
        parametros.put("encabezado", "AUTORIZACIÓN NUMERACIÓN DE FACTURACIÓN DIAN NRO. " + encabezado.getNoResolucion() + ". PREFIJO " + resolucion.getPrefijo()+"," + " RANGO AUTORIZADO DESDE " + resolucion.getDesde() + " HASTA " + resolucion.getHasta() + ". VIGENCIA FECHA INICIAL: " + getFechaVencimiento(resolucion.getFechaexpedicion()) + " FECHA FINAL: " + getFechaVencimiento(resolucion.getFechavencimiento()));

        parametros.put("numeroResolucionDian", resolucion.getNoresolucion());
        parametros.put("desdeResolucionDian", resolucion.getDesde());
        parametros.put("hastaResolucionDian", resolucion.getHasta());
        parametros.put("fechaResolucionDian", resolucion.getFechaexpedicion().toString().split(" ")[0]);

        parametros.put("numeroDocumentoEmisor", empresa.getNit());
        parametros.put("direccionEmisor", empresa.getDireccion());
        parametros.put("telefonoEmisor", empresa.getTelefono());

        parametros.put("paginaWebEmisor", empresa.getUrl());
        parametros.put("correoEmisor", empresa.getCorreo());
        parametros.put("departamentoEmisor", empresa.getDepartamento());
        parametros.put("paisEmisor", empresa.getPais());
        parametros.put("ciudadEmisor", empresa.getCiudad());

    }

    @Override
    public void agregarInformacionReceptor() {
        System.out.println("Informacion Receptor..\n");
        parametros.put("numeroDocumentoReceptor", encabezado.getNumeroDocumentoReceptor());
        if (encabezado.getRazonSocialReceptor() != null && !encabezado.getRazonSocialReceptor().equals("")) {
            parametros.put("nombreReceptor", encabezado.getRazonSocialReceptor());
        } else {
            parametros.put("nombreReceptor", encabezado.getNombreReceptor() + " " + encabezado.getApellidosReceptor());
        }
        if (encabezado.getTelefonoReceptor() != null && !encabezado.getTelefonoReceptor().equals("")) {
            parametros.put("telefonoReceptor", encabezado.getTelefonoReceptor());
        } else {
            if (encabezado.getTelefonoMovilReceptor() != null && !encabezado.getTelefonoMovilReceptor().equals("")) {
                parametros.put("telefonoReceptor", encabezado.getTelefonoMovilReceptor());
            } else {
                parametros.put("telefonoReceptor", "");
            }
        }
        parametros.put("direccionReceptor", encabezado.getDireccionReceptor());
        // parametros.put("ciudadReceptor", encabezado.getCiudadReceptor().replace("Á","A"));
        String CiudadReceptor = "";
        CiudadReceptor = encabezado.getCiudadReceptor().replace("�?", "A");
        parametros.put("ciudadReceptor", CiudadReceptor);
        //parametros.put("ciudadReceptor", CiudadReceptor);
        parametros.put("mailReceptor", encabezado.getMailReceptor());
    }

    @Override
    public void agregarDetalles() {
        for (Detalle det : detalles) {

            BigDecimal ImpuestoLinea = BigDecimal.ZERO;
            BigDecimal granTotal = BigDecimal.ZERO;

            if (det.getImpuestoLinea() != null) {
                ImpuestoLinea = ImpuestoLinea.add(det.getImpuestoLinea().setScale(2, BigDecimal.ROUND_FLOOR));
            }

            granTotal = ImpuestoLinea.add(det.getValorTotal());

            //DetalleNegocio detalle = new DetalleNegocio(det.getDescripcion(), det.getCantidad(), convertirBigDecimalAformatoDecimal(det.getValorUnitario().doubleValue()), convertirBigDecimalAformatoDecimal(det.getValorTotal().doubleValue()), "", det.getExtra1(), det.getExtra2(), det.getExtra3(), det.getExtra4(), det.getExtra5(), det.getExtra6(), det.getExtra7(), det.getExtra8(), det.getExtra9(), det.getExtra10(), det.getExtra11(), det.getExtra12(), det.getExtra13(), det.getExtra14(), det.getExtra15(), det.getExtra16(), det.getExtra17(), det.getExtra18(), det.getUnidadDeMedida(), det.getImpuestoLinea() != null ? convertirBigDecimalAformatoDecimal(det.getImpuestoLinea().doubleValue()) : "0.00", det.getTasa(), det.getTipoImpuesto(), det.getBaseImpuesto() != null ? convertirBigDecimalAformatoDecimal(det.getBaseImpuesto().doubleValue()) : "0.00", det.getSubPartidaArancelaria(), det.getIdentificacionProductos(), det.getIdDetalle());
            DetalleNegocio detalle = new DetalleNegocio(det.getDescripcion() != null ? det.getDescripcion().trim() : "" + "\n" + det.getExtra1() + "\n" + det.getExtra2() + "\n" + det.getExtra3(), det.getCantidad() != null ? det.getCantidad().trim() : "", convertirBigDecimalAformatoDecimal(det.getValorUnitario().doubleValue()), convertirBigDecimalAformatoDecimal(det.getValorTotal().doubleValue()), "", det.getExtra1(), det.getExtra2(), det.getExtra3(), det.getExtra4(), det.getExtra5(), det.getExtra6(), det.getExtra7(), det.getExtra8(), det.getExtra9(), det.getExtra10(), det.getExtra11(), det.getExtra12(), det.getExtra13(), det.getExtra14(), det.getExtra15(), det.getExtra16(), det.getExtra17(), det.getExtra18(), det.getUnidadDeMedida(), det.getImpuestoLinea() != null ? convertirBigDecimalAformatoDecimal(det.getImpuestoLinea().doubleValue()) : "0.00", det.getTipoImpuesto().equals("01") ? "IVA " + det.getTasa() + "%" : det.getTipoImpuesto().equals("04") ? "INC " + det.getTasa() + "%" : "", det.getTipoImpuesto(), det.getBaseImpuesto() != null ? convertirBigDecimalAformatoDecimal(det.getBaseImpuesto().doubleValue()) : "0.00", det.getSubPartidaArancelaria(), det.getIdentificacionProductos(), det.getIdDetalle(), convertirBigDecimalAformatoDecimal(granTotal.doubleValue()), det.getImportedescuento() != null ? convertirBigDecimalAformatoDecimal(det.getImportedescuento().doubleValue()) : "0.00");
            conceptosAReportar.add(detalle);
        }
    }

    @Override
    public void agregarImpuestos() {
        System.out.println("Impuestos..\n");
        for (Impuesto imp : impuestos) {
            if (imp.getTipoImpuesto().equals("01") || imp.getTipoImpuesto().equals("1")) {
                if (imp.getValorTotal() != null) {
                    parametros.put("iva", convertirBigDecimalAformatoDecimal((imp.getValorTotal()).doubleValue()));
                } else {
                    parametros.put("iva", "0.0");
                }
            }

            if (imp.getTipoImpuesto().equals("04") || imp.getTipoImpuesto().equals("4")) {
                if (imp.getValorTotal() != null) {
                    parametros.put("ICO", convertirBigDecimalAformatoDecimal(imp.getValorTotal().doubleValue()));
                } else {
                    parametros.put("ICO", "0.0");
                }
            }
            if (imp.getTipoImpuesto().equals("06") || imp.getTipoImpuesto().equals("6")) {
                if (imp.getValorTotal() != null) {
                    parametros.put("reteFuente", convertirBigDecimalAformatoDecimal(imp.getValorTotal().doubleValue()));
                } else {
                    parametros.put("reteFuente", "0.0");
                }
            }
            if (imp.getTipoImpuesto().equals("05") || imp.getTipoImpuesto().equals("5")) {
                if (imp.getValorTotal() != null) {
                    parametros.put("reteIVA", convertirBigDecimalAformatoDecimal(imp.getValorTotal().doubleValue()));
                } else {
                    parametros.put("reteIVA", "0.0");
                }
            }
            if (imp.getTipoImpuesto().equals("07") || imp.getTipoImpuesto().equals("7")) {
                if (imp.getValorTotal() != null) {
                    parametros.put("reteICA", convertirBigDecimalAformatoDecimal(imp.getValorTotal().doubleValue()));
                } else {
                    parametros.put("reteICA", "0.0");
                }
            }
        }
    }

    @Override
    public void agregarLogos() {
        System.out.println("Logos..\n");
        try {
            generarCodigoQr(qrdata, rutaImagenQr, "UTF-8", hintMap, 200, 200);
            parametros.put("logoEmisor", ubicacionLogo);
            //parametros.put("logo1", ubicacionLogo1);
            parametros.put("imagenQr", ubicacionQr);
        } catch (WriterException ex) {
            System.out.println("Error generarCodigoQr Vansolix: " + ex.getMessage());
            cargarError("Error generarCodigoQr Vansolix: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error generarCodigoQr Vansolix IO: " + ex.getMessage());
            cargarError("Error generarCodigoQr Vansolix IO: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void agregarDetallesNC() {
        System.out.println("Detalles NC..\n");

        parametros.put("nroFacturaAsociadaANotaCredito", encabezado.getNcIdDoc());
        parametros.put("fechaFacturaAsociadaANotaCredito", encabezado.getNcFecha());
        parametros.put("cufeFacturaAsociadaANotaCredito", encabezado.getNcUuid());
        parametros.put("conceptoNotaCredito", this.conceptosNC(encabezado.getNcCod()));

    }

    @Override
    public void agregarDetallesND() {
        System.out.println("Detalles ND..\n");

        parametros.put("nroFacturaAsociadaANotaDebito", encabezado.getNdIdDoc());
        parametros.put("fechaFacturaAsociadaANotaDebito", encabezado.getNdFecha());
        parametros.put("cufeFacturaAsociadaANotaDebito", encabezado.getNdUuid());
        parametros.put("conceptoNotaDebito", this.conceptosND(encabezado.getNdCod()));
    }

    @Override
    public void generarReporteJasper() {
        System.out.println("Generando Reporte..\n");
        try {
            jprint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(conceptosAReportar));
            JasperExportManager.exportReportToPdfFile(jprint, pdfFile.getPath());
        } catch (JRException ex) {
            System.out.println("Error Jasper Vansolix: " + ex.getMessage());
            cargarError("Error Jasper Vansolix: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String getFechaVencimiento(Date fechaVencimiento) {
        String fecha = "";
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        fecha = formatDate.format(fechaVencimiento);
        return fecha;
    }

}
