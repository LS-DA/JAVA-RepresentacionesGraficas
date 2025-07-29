/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsda.plantilla;

import com.lsda.api.PDF.UtileriasPDF;
import com.lsda.api.controllers.RestControllerClass;
import com.lsda.api.entities.Detalle;
import com.lsda.api.entities.DetalleNegocio;
import com.lsda.api.entities.Empresa;
import com.lsda.api.entities.Encabezado;
import com.lsda.api.entities.Cargo;
import com.lsda.api.entities.Factura;
import com.lsda.api.entities.Impuesto;
import com.lsda.api.entities.Resoluciones;
import com.lsda.api.repositories.FacturaRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lsda.api.repositories.FacturaProjection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.xml.sax.SAXException;

/**
 *
 * @author TEST
 */
public abstract class CreacionRepresentacion {

    //public static final String RUTA_BASE_PDF = "C:/home/Colombia/PRODUCCION_COLAPI/RepresentacionesGraficas/pdf/";
    //public static final String RUTA_BASE_IMAGENES = "C:/home/Colombia/PRODUCCION_COLAPI/RepresentacionesGraficas/imagenes/";
    //public static final String RUTA_BASE_CONFIG = "C:/home/Colombia/PRODUCCION_COLAPI/RepresentacionesGraficas/config/";
    
//Produccion Azure
     public static final String RUTA_BASE_PDF = "/Users/jjimenez/LSDA Local/Colombia/PROD_PDF/Representaciones/pdf/";
     public static final String RUTA_BASE_IMAGENES = "/Users/jjimenez/LSDA Local/Colombia/PROD_PDF/Representaciones/imagenes/";
     public static final String RUTA_BASE_CONFIG = "/Users/jjimenez/LSDA Local/Colombia/PROD_PDF/Representaciones/config/";

    //Pruebas AWS
    //public static final String RUTA_BASE_PDF = "/home/Colombia/ColAPIPRUEBAS/RepresentacionesGraficas/pdf/";
    //public static final String RUTA_BASE_IMAGENES = "/home/Colombia/ColAPIPRUEBAS/RepresentacionesGraficas/imagenes/";
    //public static final String RUTA_BASE_CONFIG = "/home/Colombia/ColAPIPRUEBAS/RepresentacionesGraficas/config/";
    private FacturaRepository facRepo;
    
    //Pruebas 
     //public static final String RUTA_BASE_PDF = "/home/Colombia/QA_PDF/Representaciones/pdf/";
     //public static final String RUTA_BASE_IMAGENES = "/home/Colombia/QA_PDF/Representaciones/imagenes/";
     //public static final String RUTA_BASE_CONFIG = "/home/Colombia/QA_PDF/Representaciones/config/";
    protected String nombreJasper;
    protected String rutaImagenQr;
    protected String rutaLogo;
    protected String rutaLogo1;
    protected String rutaLogo2;
    protected String rutaLogo3;
    protected String rutaLogo4;
    protected String rutaLogo5;
    protected String rutaLogo6;

    protected Empresa empresa;
    protected Resoluciones resolucion;

    protected String cufe;
    protected String xmlSellado;
    protected String qrdata;

    protected File pdfFile;
    protected File jasperFile;
    protected JasperReport reporte;

    protected String marcaAgua1;
    protected InputStream ubicacionMarca1 = null;

    protected JasperPrint jprint;
    protected InputStream ubicacionLogo = null;
    protected InputStream ubicacionLogo1 = null;
    protected InputStream ubicacionLogo2 = null;
    protected InputStream ubicacionLogo3 = null;
    protected InputStream ubicacionLogo4 = null;
    protected InputStream ubicacionLogo5 = null;
    protected InputStream ubicacionQr = null;

    public Map<String, Object> parametros;
    Map<EncodeHintType, ErrorCorrectionLevel> hintMap;

    protected Encabezado encabezado;
    protected List<Detalle> detalles;
    protected List<Impuesto> impuestos;
    protected List<DetalleNegocio> conceptosAReportar;
    protected List<Cargo> cargos;

    public abstract void inicializarVariables();

    public abstract void detallesFactura();

    public abstract void agregarInformacionEmisor();

    public abstract void agregarInformacionReceptor();

    public abstract void agregarDetalles();

    public abstract void agregarImpuestos();

    public abstract void agregarLogos();

    public abstract void agregarDetallesNC();

    public abstract void agregarDetallesND();

    public abstract void generarReporteJasper();

    public final void crearRepresentacionGrafica() throws FileNotFoundException, WriterException, IOException {
        inicializarVariables();
        agregarLogos();
        detallesFactura();
        agregarInformacionEmisor();
        agregarInformacionReceptor();
        agregarDetalles();
        agregarImpuestos();

        if (encabezado.getTipoComprobante().equals("91") || encabezado.getJasper().trim().equals("5")) {//Nota credito
            agregarDetallesNC();
        }
        if (encabezado.getTipoComprobante().equals("3") || encabezado.getJasper().trim().equals("3")) {//Nota credito
            agregarDetallesNC();
        }
        if (encabezado.getTipoComprobante().equals("6") || encabezado.getJasper().trim().equals("6")) {//Nota debito
            agregarDetallesND();
        }
        
        if (encabezado.getTipoComprobante().equals("92") || encabezado.getJasper().trim().equals("9")) {//Nota debito
            agregarDetallesND();
        }

        String imgQr = RUTA_BASE_IMAGENES + this.cufe + "_qr_code.png";
        generarCodigoQr(this.qrdata, imgQr, "UTF-8", hintMap, 200, 200);
        File qrFile = new File(imgQr);
        ubicacionQr = new FileInputStream(qrFile);
        this.parametros.replace("imagenQr", ubicacionQr);

        generarReporteJasper();

        try {
            ubicacionQr.close();
            qrFile.delete();
            System.out.println("Elimina imagen");
        } catch (Exception e) {
            System.out.println("No se pudo eliminar imgQR " + e.getMessage());
        }
    }

    public File getPdfFile() {
        return pdfFile;
    }

    public Encabezado getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(Encabezado encabezado) {
        this.encabezado = encabezado;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public List<Impuesto> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(List<Impuesto> impuestos) {
        this.impuestos = impuestos;
    }

    public String getNombreJasper() {
        return nombreJasper;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public void setNombreJasper(String nombreJasper) {
        this.nombreJasper = nombreJasper;
    }

    public String getQrdata() {
        return qrdata;
    }

    public void setQrdata(String qrdata) {
        this.qrdata = qrdata;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public String getXmlSellado() {
        return xmlSellado;
    }

    public void setXmlSellado(String xmlSellado) {
        this.xmlSellado = xmlSellado;
    }

    public Resoluciones getResolucion() {
        return resolucion;
    }

    public void setResolucion(Resoluciones resolucion) {
        this.resolucion = resolucion;
    }

    public void cargarError(String error) {
        RestControllerClass.setERROR_CONSUMIENDO(error);
    }

    protected final String conceptosNC(String id) {

        if (id.equals("1")) {
            return "Devolucion de parte de los bienes; no aceptacion de partes del servicio";
        }
        if (id.equals("2")) {
            return "Anulacion de factura electronica";
        }
        if (id.equals("3")) {
            return "Rebaja total aplicada";
        }
        if (id.equals("4")) {
            return "Descuento total aplicado";
        }
        if (id.equals("5")) {
            return "Rescision: Nulidad por falta de requisitos ";
        }
        if (id.equals("6")) {
            return "Otros";
        }
        return "";
    }

    protected final String conceptosND(String id) {
        if (id.equals("1")) {
            return "Intereses";
        }
        if (id.equals("2")) {
            return "Gastos por cobrar";
        }
        if (id.equals("3")) {
            return "Cambio del valor";
        }
        if (id.equals("4")) {
            return "Otros";
        }
        return "";
    }

    protected final String conceptosMetodoPago(String id) {
        String concepto;
        switch (id) {
            case "1":
                concepto = "Contado";
                break;
            case "2":
                concepto = "Crédito";
                break;
            default:
                concepto = "No especificado";
        }
        return concepto;
    }

    protected final String convertirBigDecimalAformatoMoneda(Double numero) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        formatter.setMaximumFractionDigits(0);
        return formatter.format(numero);
    }

    protected final String convertirBigDecimalAformatoMonedaCCL(Double numero) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        formatter.setMaximumFractionDigits(2);
        return formatter.format(numero);
    }

    protected final String convertirNumeroAformatoNormal(Integer numero) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(numero);
    }

    protected final String convertirBigDecimalAformatoDecimal(Double numero) {
        DecimalFormat formatter = new DecimalFormat("###,##0.00");
        return formatter.format(numero);
    }

    protected final String convertirStringADecimal(String numeroStr) {
        try {
            // Convertir el string a BigDecimal
            BigDecimal numero = new BigDecimal(numeroStr);
            
            // Formatear el BigDecimal a un string con dos decimales
            DecimalFormat formatter = new DecimalFormat("###,##0.00");
            return formatter.format(numero);
        } catch (NumberFormatException e) {
            // Manejar la excepción si el string no se puede convertir a BigDecimal
            System.err.println("Error al convertir el string a BigDecimal: " + e.getMessage());
            return null;
        }
    }
    
    protected final String CalcularDigitoVerificacion(String Nit) {
        String Temp;
        int Contador;
        int Residuo;
        int Acumulador;
        int[] Vector = new int[15];

        Vector[0] = 3;
        Vector[1] = 7;
        Vector[2] = 13;
        Vector[3] = 17;
        Vector[4] = 19;
        Vector[5] = 23;
        Vector[6] = 29;
        Vector[7] = 37;
        Vector[8] = 41;
        Vector[9] = 43;
        Vector[10] = 47;
        Vector[11] = 53;
        Vector[12] = 59;
        Vector[13] = 67;
        Vector[14] = 71;

        Acumulador = 0;

        Residuo = 0;

        for (Contador = 0; Contador < Nit.length(); Contador++) {
            Temp = String.valueOf(Nit.charAt((Nit.length() - 1) - Contador));
            Acumulador = Acumulador + (Integer.parseInt(Temp) * Vector[Contador]);
        }
        Residuo = Acumulador % 11;
        if (Residuo > 1) {
            return String.valueOf(11 - Residuo);
        }
        return String.valueOf(Residuo);
    }

    protected final String fechaValidaDIAN() throws SAXException, IOException, Exception {
        //fecha validacion
        String FechaValida = "";
        UtileriasPDF UtilPdf = new UtileriasPDF();
        Factura fac = facRepo.getFactura(cufe);
        
        
        
        FechaValida = UtilPdf.cargarFEchaValida(fac.getAppres().toString());
        //Fin fecha validacion  
        return FechaValida;
    }

    protected final String extraerFirmaDeXmlSellado(String xmlSellado) {

        String firma = "";
        //String [] divide = xmlSellado.split("<ds:X509Certificate xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">");
        String[] divide = xmlSellado.split("<ds:X509Certificate>");
        String[] divide2 = divide[1].split("</ds:X509Certificate>");
        firma = divide2[0];
        return firma;
    }

    protected final void generarCodigoQr(String qrCodeData, String filePath,
            String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight);
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
    }

    protected final String nombrarDocumento(Encabezado encabezado) {
        String filename = "";
        String pre = "face_";
        String tipDoc = "";
        String tipPer = "";
        try {

            if (encabezado.getTipoComprobante().equals("1") || encabezado.getTipoComprobante().equals("2")
                    || encabezado.getTipoComprobante().equals("3") || encabezado.getTipoComprobante().equals("4")) {
                tipDoc = "f";
            } else if (encabezado.getTipoComprobante().equals("5")) {
                tipDoc = "c";
            } else if (encabezado.getTipoComprobante().equals("6")) {
                tipDoc = "d";
            }

            String hx = Integer.toHexString(Integer.parseInt(encabezado.getFolio()));
            System.out.println("HX " + hx);
            String rp = String.format("%1$10s", hx).replace(" ", "0");
            String nitFomat = String.format("%1$10s", encabezado.getNumeroDocumentoEmisor()).replace(" ", "0");
            filename = pre + tipDoc + tipPer + nitFomat + rp;
            System.out.println("Nombre del archivo " + filename);
        } catch (Exception e) {
            e.getMessage();
        }

        String hx = Integer.toHexString(Integer.parseInt(encabezado.getFolio()));
        System.out.println("HX " + hx);
        String rp = String.format("%1$10s", hx).replace(" ", "0");
        String nitFomat = String.format("%1$10s", encabezado.getNumeroDocumentoEmisor()).replace(" ", "0");
        filename = pre + tipDoc + tipPer + nitFomat + rp;

        return filename;
    }

}
