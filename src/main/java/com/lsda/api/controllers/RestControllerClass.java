package com.lsda.api.controllers;

import com.lsda.api.entities.Correo;
import com.lsda.api.entities.Empresa;
import com.lsda.api.entities.Factura;
import com.lsda.api.entities.facPDF;
import com.lsda.api.repositories.CorreoRepository;
import com.lsda.api.repositories.EmpresaRepository;
import com.lsda.api.repositories.FacturaRepository;
import com.lsda.api.repositories.LoginRepository;
import com.lsda.api.repositories.ResolucionesRepository;
import com.lsda.api.repositories.facPDFRepository;
import com.google.zxing.WriterException;
import com.lsda.api.repositories.FacturaProjection;
import com.lsda.api.repositories.FacturaService;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.ConfigurationException;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.lsda.api.entities.EmpresaCa;
import com.lsda.api.services.EmpresaCacheService;
import com.lsda.api.services.XmlExternoService;

@RestController
@RequestMapping({"/prod/representacionGrafica"})
public class RestControllerClass {

    private final ConcurrentHashMap<String, Lock> locks = new ConcurrentHashMap<>();

    public static String ERROR_CONSUMIENDO = "";

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private facPDFRepository facPDFRepository;

    @Autowired
    private LoginRepository loginRepository;

    private Controller controller;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ResolucionesRepository resolucionRepo;

    @Autowired
    private CorreoRepository correoRepository;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private XmlExternoService xmlExternoService;

    private Empresa empresa;

    private Correo correo;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/debug-db/{cufe}")
    public ResponseEntity<String> debugDB(@PathVariable String cufe) {
        StringBuilder result = new StringBuilder();

        try (Connection conn = dataSource.getConnection()) {
            result.append("=== DIAGNÓSTICO AVANZADO ===\n");
            result.append("URL: ").append(conn.getMetaData().getURL()).append("\n");
            result.append("Driver: ").append(conn.getMetaData().getDriverName()).append("\n");
            result.append("Autocommit: ").append(conn.getAutoCommit()).append("\n");

            // Test simple de COUNT
            String sqlCount = "SELECT COUNT(*) FROM factura WITH (NOLOCK) WHERE cufe = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlCount)) {
                stmt.setQueryTimeout(30); // 30 segundos timeout
                stmt.setString(1, cufe);

                long start = System.currentTimeMillis();
                ResultSet rs = stmt.executeQuery();
                long time = System.currentTimeMillis() - start;

                rs.next();
                int count = rs.getInt(1);
                result.append("\n=== RESULTADO COUNT ===\n");
                result.append("Count: ").append(count).append("\n");
                result.append("Tiempo: ").append(time).append("ms\n");

                if (time > 1000) {
                    result.append("🚨 PROBLEMA: La consulta tarda más de 1 segundo\n");
                } else {
                    result.append("✅ BUENO: Consulta rápida\n");
                }
            }

            // Test de consulta completa
            if (result.toString().contains("BUENO")) {
                String sqlFull = "SELECT id, cufe FROM factura WITH (NOLOCK) WHERE cufe = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sqlFull)) {
                    stmt.setQueryTimeout(30);
                    stmt.setString(1, cufe);

                    long start = System.currentTimeMillis();
                    ResultSet rs = stmt.executeQuery();
                    long time = System.currentTimeMillis() - start;

                    result.append("\n=== RESULTADO FULL ===\n");
                    if (rs.next()) {
                        result.append("ID: ").append(rs.getLong("id")).append("\n");
                        result.append("CUFE: ").append(rs.getString("cufe").substring(0, 20)).append("...\n");
                    }
                    result.append("Tiempo: ").append(time).append("ms\n");
                }
            }

        } catch (Exception e) {
            result.append("ERROR: ").append(e.getMessage()).append("\n");
            e.printStackTrace();
        }

        return ResponseEntity.ok(result.toString());
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/ConsultaPDF/{Cufe}"})
    @ResponseBody
    @Transactional(readOnly = true)
    public JSONObject add(@PathVariable String Cufe) throws ParserConfigurationException, SAXException, IOException, ConfigurationException, WriterException, ParseException, JSONException, Exception {
        JSONObject response = new JSONObject();
        System.out.println("1");

        String xmlBase = null;
        String xmlSellado = null;
        String qrdata = null;
        String FechaValida = "";
        String FechaValida2 = "";

        System.out.println("2");

        Lock lock = this.locks.computeIfAbsent(Cufe, k -> new ReentrantLock());

        System.out.println("3");

        lock.lock();
        System.out.println("4");

        try {

            System.out.println("5");

            long inicio = System.currentTimeMillis();
            // Factura factura = this.facturaRepository.getFactura(Cufe);
            Factura factura = facturaService.getFacturaOptimizada(Cufe);

            EmpresaCa empresa = EmpresaCacheService.getEmpresaByNit(factura.getNitemisor());

            //Factura factura = this.facturaJdbcRepository.getFacturaByCufe(Cufe);
            //  FacturaProjection factura = this.facturaRepository.getFactura(Cufe);
            long fin = System.currentTimeMillis();
            long tiempoEjecucion = fin - inicio;
            System.out.println("Tiempo de ejecucide getFactura: " + tiempoEjecucion + " ms");
            if (factura != null) {
                // Fetch xmlBase from URL
                String xmlBaseUrl = factura.getBase64doc();
                if (xmlBaseUrl != null && !xmlBaseUrl.isEmpty()) {
                    xmlBase = fetchContentFromUrl(xmlBaseUrl);
                    if (xmlBase == null) {
                        response.put("error", "No se pudo obtener el contenido de la URL de base64doc: " + xmlBaseUrl);
                        return response;
                    }
                } else {
                    response.put("error", "El campo base64doc es nulo o vacío");
                    return response;
                }
                // Fetch xmlSellado from URL
                String xmlSelladoUrl = factura.getxml();
                if (xmlSelladoUrl != null && !xmlSelladoUrl.isEmpty()) {
                    xmlSellado = fetchContentFromUrl(xmlSelladoUrl);
                    if (xmlSellado == null) {

                        if (empresa != null) {
                            long inicioServicio = System.currentTimeMillis();
                            xmlSellado = xmlExternoService.obtenerXmlSelladoValidado(Cufe, empresa.getNit());
                            long tiempoServicio = System.currentTimeMillis() - inicioServicio;

                            if (xmlSellado != null) {
                                System.out.println("✅ XML sellado obtenido del servicio externo en " + tiempoServicio + "ms");
                            } else {
                                response.put("error", "No se pudo obtener XML sellado ni de URL ni de servicio externo");
                                return response;
                            }
                        } else {
                            response.put("error", "No se pudo determinar el NIT de la empresa para consultar servicio externo");
                            return response;
                        }

                    }
                } else {

                    if (empresa != null) {
                        long inicioServicio = System.currentTimeMillis();
                        xmlSellado = xmlExternoService.obtenerXmlSelladoValidado(Cufe, empresa.getNit());
                        long tiempoServicio = System.currentTimeMillis() - inicioServicio;

                        if (xmlSellado != null) {
                            System.out.println("✅ XML sellado obtenido del servicio externo en " + tiempoServicio + "ms");
                        } else {
                            response.put("error", "No se pudo obtener XML sellado ni de URL ni de servicio externo");
                            return response;
                        }
                    } else {
                        response.put("error", "No se pudo determinar el NIT de la empresa para consultar servicio externo");
                        return response;
                    }
                }
                qrdata = factura.getQrdata();
                if (qrdata == null) {
                    qrdata = "https://catalogo-vpfe.dian.gov.co/document/searchqr?documentkey=" + Cufe;
                }

                // Fetch xmlSellado from URL
                String Appres = factura.getxml();
                if (Appres != null && !Appres.isEmpty()) {
                    Appres = fetchContentFromUrl(Appres);
                    if (Appres == null) {

                        if (empresa != null) {
                            long inicioServicio = System.currentTimeMillis();
                            Appres = xmlExternoService.extraerXmlDeJsonAlternativo(Cufe, empresa.getNit());
                            long tiempoServicio = System.currentTimeMillis() - inicioServicio;

                            if (Appres != null) {
                                System.out.println("✅ XML sellado obtenido del servicio externo en " + tiempoServicio + "ms");
                            } else {
                                response.put("error", "No se pudo obtener XML sellado ni de URL ni de servicio externo");
                                return response;
                            }
                        } else {
                            response.put("error", "No se pudo determinar el NIT de la empresa para consultar servicio externo");
                            return response;
                        }

                    }
                } else {
                    if (empresa != null) {
                        long inicioServicio = System.currentTimeMillis();
                        Appres = xmlExternoService.extraerXmlDeJsonAlternativo(Cufe, empresa.getNit());
                        long tiempoServicio = System.currentTimeMillis() - inicioServicio;

                        if (Appres != null) {
                            System.out.println("✅ XML sellado obtenido del servicio externo en " + tiempoServicio + "ms");
                        } else {
                            response.put("error", "No se pudo obtener XML sellado ni de URL ni de servicio externo");
                            return response;
                        }
                    } else {
                        response.put("error", "No se pudo determinar el NIT de la empresa para consultar servicio externo");
                        return response;
                    }
                }

                FechaValida = cargarFEchaValida(Appres);
                FechaValida2 = cargarFEchaValida2(Appres);
                this.controller = new Controller(xmlSellado, xmlBase, Cufe, qrdata, this.empresaRepository, this.resolucionRepo, FechaValida, FechaValida2);
                String pdfBase64 = this.controller.generarPDF();
                if (!pdfBase64.equals("")) {
                    response.put("pdf64", pdfBase64);
                } else {
                    response.put("error", "No pudo cargarse el PDF: " + ERROR_CONSUMIENDO);
                }
            } else {
                response.put("error", "No se encontrfactura con CUFE: " + Cufe);
            }
        } finally {
            lock.unlock();
            this.locks.remove(Cufe);
        }
        return response;
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/ConsultaPDF3/{Cufe}"})
    @ResponseBody
    @Transactional(readOnly = true)
    public JSONObject ConsultaPDF3(@PathVariable String Cufe) throws SAXException, IOException, Exception {
        String xmlBase = null;
        String xmlSellado = null;
        String qrdata = null;
        JSONObject response = new JSONObject();
        String FechaValida = "";
        String FechaValida2 = "";
        Factura factura = null;
        long inicio = System.currentTimeMillis();
        factura = this.facturaRepository.getFactura(Cufe);

        // FacturaProjection factura = this.facturaRepository.getFactura(Cufe);
        long fin = System.currentTimeMillis();
        long tiempoEjecucion = fin - inicio;
        System.out.println("Tiempo de ejecucide getFactura: " + tiempoEjecucion + " ms");
        if (factura != null) {
            xmlBase = factura.getBase64doc().toString();
            xmlBase = new String(Base64.getDecoder().decode(xmlBase));
            xmlSellado = factura.getxml();
            xmlSellado = new String(Base64.getDecoder().decode(xmlSellado));
            qrdata = "https://catalogo-vpfe.dian.gov.co/document/searchqr?documentkey=" + Cufe;
            qrdata = factura.getQrdata();
            FechaValida = cargarFEchaValida(factura.getAppres().toString());
            FechaValida2 = cargarFEchaValida2(factura.getAppres().toString());
            this.controller = new Controller(xmlSellado, xmlBase, Cufe, qrdata, this.empresaRepository, this.resolucionRepo, FechaValida, FechaValida2);
            String pdfBase64 = this.controller.generarPDF();
            if (!pdfBase64.equals("")) {
                response.put("pdf64", pdfBase64);
            } else {
                response.put("error", "No pudo cargarse el PDF: " + ERROR_CONSUMIENDO);
            }
        }
        return response;
    }

    public static String getERROR_CONSUMIENDO() {
        return ERROR_CONSUMIENDO;
    }

    public static void setERROR_CONSUMIENDO(String ERROR_CONSUMIENDO) {
        com.lsda.api.controllers.RestControllerClass.ERROR_CONSUMIENDO = ERROR_CONSUMIENDO;
    }

    public static String decode(String value) throws Exception {
        byte[] decodedValue = Base64.getDecoder().decode(value);
        return new String(decodedValue, StandardCharsets.ISO_8859_1);
    }

    public String cargarFEchaValida2(String xml) throws ParserConfigurationException, SAXException, IOException, Exception {

        String fechavalida = "";
        String horavalida = "";

        JSONParser parser2 = new JSONParser();

        try {
            JSONObject jsonDian2 = (JSONObject) parser2.parse(xml);
            xml = decode(jsonDian2.get("Xml").toString()).toString();

            if (!"".equals(xml)) {

                fechavalida = xml.substring(xml.indexOf("<cbc:IssueDate>") + 15, xml.indexOf("<cbc:IssueDate>") + 25);
                horavalida = xml.substring(xml.indexOf("<cbc:IssueTime>") + 15, xml.indexOf("<cbc:IssueTime>") + 29);
                fechavalida = fechavalida + " " + horavalida;
                if (fechavalida.length() > 9) {
                    fechavalida = fechavalida.substring(0, fechavalida.length() - 9);
                }
            } else {
                fechavalida = "9999/99/99 ";
            }

        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(facPDF.class.getName()).log(Level.SEVERE, null, ex);
            fechavalida = "9999/99/99 ";
        } catch (Exception e) {
            Logger.getLogger(facPDF.class.getName()).log(Level.SEVERE, null, e);
            fechavalida = "9999/99/99 ";
        }

        return fechavalida;

    }

    public String cargarFEchaValida(String xml) throws ParserConfigurationException, SAXException, IOException, Exception {

        String fechavalida = "";
        String horavalida = "";

        JSONParser parser2 = new JSONParser();

        try {
            JSONObject jsonDian2 = (JSONObject) parser2.parse(xml);
            xml = decode(jsonDian2.get("Xml").toString()).toString();

            if (!"".equals(xml)) {

                fechavalida = xml.substring(xml.indexOf("<cbc:IssueDate>") + 15, xml.indexOf("<cbc:IssueDate>") + 25);
                horavalida = xml.substring(xml.indexOf("<cbc:IssueTime>") + 15, xml.indexOf("<cbc:IssueTime>") + 29);
                fechavalida = "Fecha y hora de validacion DIAN " + fechavalida + " " + horavalida;
            } else {
                fechavalida = "Fecha y hora de validacion DIAN ";
            }

        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(facPDF.class.getName()).log(Level.SEVERE, null, ex);
            fechavalida = "Fecha y hora de validacion DIAN ";
        } catch (Exception e) {
            Logger.getLogger(facPDF.class.getName()).log(Level.SEVERE, null, e);
            fechavalida = "Fecha y hora de validacion DIAN ";
        }

        return fechavalida;

    }

    private String fetchContentFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); // 5 seconds timeout
        connection.setReadTimeout(5000); // 5 seconds timeout
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return content.toString();
        } else {
            connection.disconnect();
            return null;
        }
    }
}
