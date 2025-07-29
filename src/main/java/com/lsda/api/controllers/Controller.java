/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsda.api.controllers;

import com.lsda.api.PDF.GeneracionPDF;
import com.lsda.api.entities.Detalle;
import com.lsda.api.entities.Encabezado;
import com.lsda.api.entities.Impuesto;
import com.lsda.api.entities.Cargo;
import com.lsda.api.entities.DescuentoLinea;
import com.lsda.api.repositories.EmpresaRepository;
import com.lsda.api.repositories.ResolucionesRepository;
import com.google.zxing.WriterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.naming.ConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author TEST
 */
public class Controller {

    private String xmlBase, cufe, qrdata, xmlSellado, fechaValida,fechaValida2;
    private EmpresaRepository empresaRepo;
    private ResolucionesRepository resolucionRepo;
    private HashMap<String, String> resultado;

    @Value("${api.token}")
    private String token;
    @Value("${api.pdf}")
    private String pathPDF;

    private final String username = "representacionesGraficas";
    private final String password = "representacionesGraficas";

    public Controller(String xmlSellado, String xmlBase, String cufe, String qrdata, EmpresaRepository empresaRepo, ResolucionesRepository resolucionRepo, String fechaValida, String fechaValida2) {
        this.xmlSellado = xmlSellado;
        this.xmlBase = xmlBase;
        this.cufe = cufe;
        this.qrdata = qrdata;
        this.empresaRepo = empresaRepo;
        this.resolucionRepo = resolucionRepo;
        this.xmlSellado = xmlSellado;
        this.fechaValida = fechaValida;
        this.fechaValida2 = fechaValida2;

    }

    private Encabezado obtenerEncabezado(Document xml, String FechaVal, String FechaVal2) {
        Encabezado encabezado = new Encabezado();
        NodeList nlist2 = xml.getElementsByTagName("Encabezado");

        for (int i = 0; i < nlist2.getLength(); i++) {
            Node nod = nlist2.item(i);
            if (nod.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nod;
                encabezado.setLlaveComprobante(getTagValue("llavecomprobante", e));
                encabezado.setTotal(new BigDecimal(getTagValue("total", e)));
                encabezado.setNoResolucion(getTagValue("noresolucion", e));
                encabezado.setPrefijo(getTagValue("prefijo", e));
                encabezado.setSubtotal(new BigDecimal(getTagValue("subtotal", e)));
                encabezado.setTipoComprobante(getTagValue("tipocomprobante", e));
                encabezado.setFolio(getTagValue("folio", e));
                encabezado.setFecha(getTagValue("fecha", e));
                encabezado.setTotalDescuentos(new BigDecimal(getTagValue("totaldescuentos", e)));
                encabezado.setNumeroDocumentoEmisor(getTagValue("nitemisor", e));
                encabezado.setNumeroDocumentoReceptor(getTagValue("nitreceptor", e));
                encabezado.setTotalImpuestos(new BigDecimal(getTagValue("totalimpuestos", e)));
                encabezado.setMontoLetra(getTagValue("montoletra", e));
                encabezado.setJasper(getTagValue("xslt", e));
                encabezado.setExtra1(getTagValue("extra1", e));
                encabezado.setExtra2(getTagValue("extra2", e));
                encabezado.setExtra3(getTagValue("extra3", e));
                encabezado.setHora(getTagValue("hora", e));
                encabezado.setTextoLibre(getTagValue("textolibre", e));
                encabezado.setMoneda(getTagValue("moneda", e));
                encabezado.setTipoReceptor(getTagValue("tiporeceptor", e));
                encabezado.setDepartamentoReceptor(getTagValue("departamentorceptor", e));
                encabezado.setCiudadReceptor(getTagValue("ciudadreceptor", e));
                encabezado.setDireccionReceptor(getTagValue("direccionreceptor", e));
                encabezado.setTelefonoReceptor(getTagValue("telefonoreceptor", e));
                encabezado.setTelefonoMovilReceptor(getTagValue("telefonomovilreceptor", e));
                encabezado.setPaisReceptor(getTagValue("paisreceptor", e));
                encabezado.setRegimenReceptor(getTagValue("regimenreceptor", e));
                encabezado.setNombreReceptor(getTagValue("nombrereceptor", e));
                encabezado.setSegNomReceptor(getTagValue("segnombrereceptor", e));
                encabezado.setApellidosReceptor(getTagValue("apellidosreceptor", e));
                try {
                    encabezado.setTotalImpuestosRetenidos(new BigDecimal(getTagValue("totalimpuestosretenidos", e)));
                } catch (Exception re) {
                    encabezado.setTotalImpuestosRetenidos(new BigDecimal("0.00"));
                }
                encabezado.setMailReceptor(getTagValue("mailreceptor", e));
                encabezado.setNcIdFact(getTagValue("ncidfact", e));
                encabezado.setNcCod(getTagValue("nccod", e));
                encabezado.setNcIdDoc(getTagValue("nciddoc", e));
                encabezado.setNcUuid(getTagValue("ncuuid", e));
                encabezado.setNcFecha(getTagValue("ncfecha", e));
                encabezado.setNdIdFact(getTagValue("ndidfact", e));
                encabezado.setNdCod(getTagValue("ndcod", e));
                encabezado.setNdIdDoc(getTagValue("ndiddoc", e));
                encabezado.setNdUuid(getTagValue("nduuid", e));
                encabezado.setNdFecha(getTagValue("ndfecha", e));
                encabezado.setExtra4(getTagValue("extra4", e));
                encabezado.setExtra5(getTagValue("extra5", e));
                //encabezado.setExtra5(new BigDecimal(getTagValue("extra5", e)));
                encabezado.setExtra6(getTagValue("extra6", e));
                encabezado.setExtra7(getTagValue("extra7", e));
                encabezado.setExtra8(getTagValue("extra8", e));
                encabezado.setExtra9(getTagValue("extra9", e));
                encabezado.setExtra10(getTagValue("extra10", e));
                encabezado.setExtra11(getTagValue("extra11", e));
                encabezado.setExtra12(getTagValue("extra12", e));
                encabezado.setExtra13(getTagValue("extra13", e));
                encabezado.setExtra14(getTagValue("extra14", e));
                encabezado.setExtra15(getTagValue("extra15", e));
                encabezado.setExtra16(getTagValue("extra16", e));
                encabezado.setExtra17(getTagValue("extra17", e));
                encabezado.setExtra18(getTagValue("extra18", e));

                //Campos Adicionales Nuevo Modelo
                encabezado.setDigitoVerificacion(getTagValue("digitoverificacion", e));
                encabezado.setTributoReceptor(getTagValue("tributoreceptor", e));
                encabezado.setNombreComercialReceptor(getTagValue("nombrecomercialreceptor", e));
                encabezado.setCodigoDepartamento(getTagValue("codigodepartamento", e));
                encabezado.setCodigoCiudadReceptor(getTagValue("codigociudadreceptor", e));
                encabezado.setCodigoPostalReceptor(getTagValue("codigopostal", e));
                encabezado.setMailReceptorContactoReceptor(getTagValue("mailreceptorcontacto", e));
                encabezado.setNombreContactoReceptor(getTagValue("nombrecontactoreceptor", e));
                encabezado.setMetodoDePago(getTagValue("metodopago", e));
                encabezado.setMedioDePago(getTagValue("mediopago", e));
                encabezado.setFechaVencimientoFactura(getTagValue("fechavencimiento", e));
                encabezado.setTerminosDePago(getTagValue("terminospago", e));
                encabezado.setBaseImpuestos(!getTagValue("baseimpuesto", e).equals("") ? new BigDecimal(getTagValue("baseimpuesto", e)) : new BigDecimal("0.00"));
                encabezado.setTotalSinDescuentos(!getTagValue("totalsindescuento", e).equals("") ? new BigDecimal(getTagValue("totalsindescuento", e)) : new BigDecimal("0.00"));
                encabezado.setTasaDeCambio(getTagValue("tasacambio", e));
                encabezado.setIncoterm(getTagValue("incoterm", e));
                encabezado.setTotalCargos(getTagValue("totalcargos", e));
                //Campos Adicionales Nuevo Modelo
                encabezado.setFechaValida(FechaVal);
                encabezado.setFechaValida2(FechaVal2);

                System.out.println("Monto Letra: " + encabezado.getMontoLetra());
            }
        }
        return encabezado;
    }

    private DescuentoLinea obtenerDescuentosLineaValor(Document xml, String idLineaD) {

        DescuentoLinea descuentoLinea = null;
        String idLinea = "";

        NodeList nlist = xml.getElementsByTagName("detDesc");
        for (int i = 0; i < nlist.getLength(); i++) {
            Node nod = nlist.item(i);
            if (nod.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nod;
                idLinea = getTagValue("idlineaDescuento", e);
                if (idLineaD.equals(idLinea)) {
                    descuentoLinea = new DescuentoLinea();
                    descuentoLinea.setDescripcion(getTagValue("razondescuentodet", e));
                    descuentoLinea.setValorDescuento(new BigDecimal(getTagValue("importedescuentodet", e)));
                    break;

                }

            }

        }

        return descuentoLinea;

    }

    private List<Detalle> obtenerDetalles(Document xml) {
        List<Detalle> detalles = new ArrayList();
        Detalle detalle;

        NodeList nlist = xml.getElementsByTagName("Det");
        for (int i = 0; i < nlist.getLength(); i++) {
            Node nod = nlist.item(i);
            if (nod.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nod;
                detalle = new Detalle();
                detalle.setLlaveComprobante(getTagValue("llaveComprobante", e));
                detalle.setIdDetalle(getTagValue("idConcepto", e));

                detalle.setValorTotal(new BigDecimal(getTagValue("importe", e)));
                detalle.setDescripcion(getTagValue("descripcion", e));
                detalle.setValorUnitario(new BigDecimal(getTagValue("precioUnitario", e)));
                detalle.setCantidad(getTagValue("cantidad", e));
                detalle.setInformacionAdicional(getTagValue("adicional_info", e));
                detalle.setExtra1(getTagValue("extra1", e));
                detalle.setExtra2(getTagValue("extra2", e));
                detalle.setExtra3(getTagValue("extra3", e));
                detalle.setExtra4(getTagValue("extra4", e));
                detalle.setExtra5(getTagValue("extra5", e));
                detalle.setExtra6(getTagValue("extra6", e));
                detalle.setExtra7(getTagValue("extra7", e));
                detalle.setExtra8(getTagValue("extra8", e));
                detalle.setExtra9(getTagValue("extra9", e));
                detalle.setExtra10(getTagValue("extra10", e));
                detalle.setExtra11(getTagValue("extra11", e));
                detalle.setExtra12(getTagValue("extra12", e));
                detalle.setExtra13(getTagValue("extra13", e));
                detalle.setExtra14(getTagValue("extra14", e));
                detalle.setExtra15(getTagValue("extra15", e));

                //Campos Adicionales Nuevo Modelo
                detalle.setUnidadDeMedida(getTagValue("unidadmedida", e));
                detalle.setImpuestoLinea(!getTagValue("impuestolinea", e).equals("") ? new BigDecimal(getTagValue("impuestolinea", e)) : new BigDecimal("0.00"));
                detalle.setTasa(getTagValue("tasa", e));
                detalle.setTipoImpuesto(getTagValue("tipo", e));
                detalle.setBaseImpuesto(!getTagValue("baseimpuestos", e).equals("") ? new BigDecimal(getTagValue("baseimpuestos", e)) : new BigDecimal("0.00"));
                detalle.setSubPartidaArancelaria(getTagValue("subpartidaarancelaria", e));
                detalle.setIdentificacionProductos(getTagValue("identificacionproductos", e));
                //Campos Adicionales Nuevo Modelo

                // Obtener y asignar descuentos
                DescuentoLinea descuento = obtenerDescuentosLineaValor(xml, detalle.getIdDetalle());
                if (descuento != null) {
                    detalle.setImportedescuento(descuento.getValorDescuento());
                    detalle.setDescripciondescuento(descuento.getDescripcion());
                } else {
                    detalle.setImportedescuento(BigDecimal.ZERO);
                    detalle.setDescripciondescuento("");
                }

                detalles.add(detalle);
            }
        }
        return detalles;
    }

    private List<Impuesto> obtenerImpuestos(Document xml) {
        List<Impuesto> impuestos = new ArrayList();
        Impuesto impuesto;
        NodeList nlist = xml.getElementsByTagName("Imp");
        for (int i = 0; i < nlist.getLength(); i++) {
            Node nod = nlist.item(i);
            if (nod.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nod;
                impuesto = new Impuesto();
                impuesto.setLlaveComprobante(getTagValue("llaveComprobante", e));
                impuesto.setIdImpuesto(getTagValue("idImpuesto", e));
                impuesto.setValorTotal(new BigDecimal(getTagValue("importe", e)));
                impuesto.setTipoImpuesto(getTagValue("tipoImpuesto", e));
                impuesto.setTasa(new BigDecimal(getTagValue("tasa", e)));
                impuesto.setRetenido(getTagValue("retenido", e));

                //Campos Adicionales Nuevo Modelo
                impuesto.setBaseImpuestos(!getTagValue("baseimpuestos", e).equals("") ? new BigDecimal(getTagValue("baseimpuestos", e)) : new BigDecimal("0.00"));
                //Campos Adicionales Nuevo Modelo
                impuestos.add(impuesto);
            }
        }
        return impuestos;
    }

    private List<Cargo> obtenerCargos(Document xml) {
        List<Cargo> cargos = new ArrayList();
        Cargo cargo;
        NodeList nlist = xml.getElementsByTagName("Car");
        for (int i = 0; i < nlist.getLength(); i++) {
            Node nod = nlist.item(i);
            if (nod.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nod;
                cargo = new Cargo();
                cargo.setLlaveComprobante(getTagValue("llaveComprobante", e));
                cargo.setrazon(getTagValue("razon", e));
                cargo.setCodCargo((getTagValue("codcargo", e)));
                cargo.setPorcentaje(getTagValue("porcentaje", e));
                cargo.setBasecargo(new BigDecimal(getTagValue("basecargo", e)));
                cargo.setTotal(getTagValue("importe", e));

                cargos.add(cargo);
            }
        }
        return cargos;
    }

    public String generarPDF() throws ParserConfigurationException, SAXException, IOException, ConfigurationException, WriterException, ParseException, Exception {
        Document xml = getXMLDocument(this.xmlBase);
        Encabezado encabezado = this.obtenerEncabezado(xml, this.fechaValida,this.fechaValida2 );
        List<Detalle> detalles = this.obtenerDetalles(xml);
        List<Impuesto> impuestos = this.obtenerImpuestos(xml);
        List<Cargo> cargos = this.obtenerCargos(xml);
        GeneracionPDF genPDF = new GeneracionPDF(qrdata, cufe, encabezado, detalles, impuestos, cargos, xmlSellado, empresaRepo, resolucionRepo);
        String pdfBase64 = genPDF.generarFilePDF();
        return pdfBase64;
    }

    private Document getXMLDocument(String xmlString) throws ParserConfigurationException, SAXException, IOException {
        //System.out.println("XML antes:\n"+xmlString);
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(new InputSource(new StringReader(xmlString)));

        return document;
    }

    private String getTagValue(String nom, Element e) {
        String p = "";
        try {
            NodeList lst = e.getElementsByTagName(nom).item(0).getChildNodes();
            Node n = (Node) lst.item(0);
            if (n == null) {
                return p;
            } else {
                p = n.getNodeValue();

                /*     String s = p;
                byte[] b = s.getBytes(StandardCharsets.ISO_8859_1);

               p= new String(b, StandardCharsets.UTF_8);
                 */
                return p;
            }
        } catch (Exception m) {
            return p;
        }

    }

    
    public HashMap<String, String> getResultado() {
        return resultado;
    }

    public void setResultado(JSONObject response) throws JSONException {
        resultado = new HashMap<String, String>();
        resultado.put("cufe", cufe);
        resultado.put("Resultado", response.has("Resultado") ? response.getString("Resultado") : "");
        resultado.put("Error", response.has("Error") ? response.getString("Error") : "");

    }
}