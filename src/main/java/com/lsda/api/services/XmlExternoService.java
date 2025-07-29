package com.lsda.api.services;

import com.lsda.api.dto.XmlServiceRequest;
import com.lsda.api.dto.XmlServiceResponse;
import com.lsda.api.entities.EmpresaCa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Arrays;

@Service
public class XmlExternoService {

    private static final String XML_SERVICE_URL = "http://enviador.ls-da.com/ServicioDian/service/XmlByDocumentKey/Trackid";
    
        private static final String AR_SERVICE_URL = "http://enviador.ls-da.com/ServicioDian/service/StatusDIANProd/Trackid";


    @Autowired
    private RestTemplate restTemplate;

    // Token JWT desde configuración
    @Value("${app.xml.service.token:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqamltZW5leiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTY3MjA2NDcyNTk2MCwiZXhwIjoxNjcyMDc0NzI1fQ.LBpfb48xkat5-V92x7QAaNDY0rLYBHbfLi5102K_BkAj6v18MFR8leVH9y9MqEAwV0aKGyOv1_fN9Zxn480ouA}")
    private String jwtToken;

    /**
     * Obtiene el XML sellado desde el servicio externo usando el CUFE y datos de empresa
     */
    public String obtenerXmlSellado(String cufe, String nitEmpresa) {
        try {
            // Obtener datos de la empresa desde el caché
            EmpresaCa empresa = EmpresaCacheService.getEmpresaByNit(nitEmpresa);
            if (empresa == null) {
                System.err.println("❌ No se encontró empresa con NIT: " + nitEmpresa);
                return null;
            }

            System.out.println("🔄 Consultando XML sellado para CUFE: " + cufe);
            System.out.println("   📍 Empresa: " + empresa.getNombre() + " (NIT: " + nitEmpresa + ")");

            // Crear el request
            XmlServiceRequest request = new XmlServiceRequest(cufe, nitEmpresa, empresa.getPasscert());

            // *** CONFIGURAR HEADERS CON AUTHORIZATION ***
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(
                MediaType.APPLICATION_JSON,
                MediaType.TEXT_PLAIN,
                MediaType.ALL
            ));
            
            // *** AGREGAR JWT TOKEN ***
            headers.add("Authorization", jwtToken);
            headers.add("User-Agent", "LSDA-API/1.0");
            headers.add("Cache-Control", "no-cache");
            headers.add("Connection", "keep-alive");

            HttpEntity<XmlServiceRequest> entity = new HttpEntity<>(request, headers);

            System.out.println("📤 Enviando request:");
            System.out.println("   URL: " + XML_SERVICE_URL);
            System.out.println("   Authorization: " + jwtToken.substring(0, 50) + "...");
            System.out.println("   Body: " + request);

            // Realizar la llamada POST
            long inicio = System.currentTimeMillis();
            ResponseEntity<XmlServiceResponse> response = restTemplate.exchange(
                XML_SERVICE_URL,
                HttpMethod.POST,
                entity,
                XmlServiceResponse.class
            );
            long tiempo = System.currentTimeMillis() - inicio;

            System.out.println("📥 Respuesta recibida:");
            System.out.println("   Status: " + response.getStatusCode());
            System.out.println("   Headers: " + response.getHeaders());

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String xmlBase64 = response.getBody().getXml();
                
                if (xmlBase64 != null && !xmlBase64.trim().isEmpty()) {
                    // Decodificar el XML de Base64
                    String xmlDecodificado = decodificarBase64(xmlBase64);
                    
                    if (xmlDecodificado != null) {
                        System.out.println("✅ XML sellado obtenido exitosamente:");
                        System.out.println("   ⏱️ Tiempo de respuesta: " + tiempo + "ms");
                        System.out.println("   📄 Tamaño XML: " + xmlDecodificado.length() + " caracteres");
                        return xmlDecodificado;
                    } else {
                        System.err.println("❌ Error al decodificar XML Base64");
                        return null;
                    }
                } else {
                    System.err.println("❌ Respuesta vacía del servicio XML");
                    return null;
                }
            } else {
                System.err.println("❌ Error en respuesta del servicio XML. Status: " + response.getStatusCode());
                return null;
            }

        } catch (Exception e) {
            System.err.println("❌ Error al consultar servicio XML externo: " + e.getMessage());
            e.printStackTrace();
            
            // Intentar método alternativo
            return obtenerXmlSelladoAlternativo(cufe, nitEmpresa);
        }
    }

    /**
     * Método alternativo usando String como respuesta
     */
    private String obtenerXmlSelladoAlternativo(String cufe, String nitEmpresa) {
        try {
            System.out.println("🔄 Intentando método alternativo con Authorization...");
            
            EmpresaCa empresa = EmpresaCacheService.getEmpresaByNit(nitEmpresa);
            if (empresa == null) {
                return null;
            }

            // JSON manual como String
            String jsonBody = String.format(
                "{\"trackid\":\"%s\",\"nit\":\"%s\",\"passCert\":\"%s\"}",
                cufe, nitEmpresa, empresa.getPasscert()
            );

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=utf-8");
            headers.add("Accept", "*/*");
            headers.add("Authorization", jwtToken); // *** JWT TOKEN ***
            headers.add("User-Agent", "LSDA-API/1.0");

            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            System.out.println("📤 Request alternativo:");
            System.out.println("   Authorization: " + jwtToken.substring(0, 50) + "...");
            System.out.println("   Body: " + jsonBody);

            // Respuesta como String crudo
            ResponseEntity<String> response = restTemplate.exchange(
                XML_SERVICE_URL,
                HttpMethod.POST,
                entity,
                String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                System.out.println("📥 Respuesta cruda: " + responseBody);

                // Parsear manualmente el JSON
                String xmlBase64 = extraerXmlDeJson(responseBody);
                if (xmlBase64 != null) {
                    String xmlDecodificado = decodificarBase64(xmlBase64);
                    if (xmlDecodificado != null) {
                        System.out.println("✅ XML obtenido con método alternativo");
                        return xmlDecodificado;
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Error en método alternativo: " + e.getMessage());
        }

        return null;
    }

    
    
    public String extraerXmlDeJsonAlternativo(String cufe, String nitEmpresa) {
        try {
            System.out.println("🔄 Intentando método alternativo con Authorization...");
            
            EmpresaCa empresa = EmpresaCacheService.getEmpresaByNit(nitEmpresa);
            if (empresa == null) {
                return null;
            }

            // JSON manual como String
            String jsonBody = String.format(
                "{\"trackid\":\"%s\",\"nit\":\"%s\",\"passCert\":\"%s\"}",
                cufe, nitEmpresa, empresa.getPasscert()
            );

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=utf-8");
            headers.add("Accept", "*/*");
            headers.add("Authorization", jwtToken); // *** JWT TOKEN ***
            headers.add("User-Agent", "LSDA-API/1.0");

            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            System.out.println("📤 Request alternativo:");
            System.out.println("   Authorization: " + jwtToken.substring(0, 50) + "...");
            System.out.println("   Body: " + jsonBody);

            // Respuesta como String crudo
            ResponseEntity<String> response = restTemplate.exchange(
                AR_SERVICE_URL,
                HttpMethod.POST,
                entity,
                String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                System.out.println("📥 Respuesta cruda: " + responseBody);

                // Parsear manualmente el JSON
                if (responseBody != null) {
                    
                        System.out.println("✅ XML obtenido con método alternativo");
                        return responseBody;
                    
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Error en método alternativo: " + e.getMessage());
        }

        return null;
    }
    
    
    
    /**
     * Extrae el valor de "Xml" de un JSON string manualmente
     */
    private String extraerXmlDeJson(String jsonResponse) {
        try {
            if (jsonResponse == null) return null;
            
            // Buscar "Xml":"valor"
            int xmlStart = jsonResponse.indexOf("\"Xml\"");
            if (xmlStart == -1) return null;
            
            int colonIndex = jsonResponse.indexOf(":", xmlStart);
            if (colonIndex == -1) return null;
            
            int quoteStart = jsonResponse.indexOf("\"", colonIndex);
            if (quoteStart == -1) return null;
            
            int quoteEnd = jsonResponse.indexOf("\"", quoteStart + 1);
            if (quoteEnd == -1) return null;
            
            return jsonResponse.substring(quoteStart + 1, quoteEnd);
            
        } catch (Exception e) {
            System.err.println("❌ Error al parsear JSON manualmente: " + e.getMessage());
            return null;
        }
    }

    /**
     * Decodifica un string Base64 a texto XML
     */
    private String decodificarBase64(String base64String) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String.trim());
            return new String(decodedBytes, "UTF-8");
        } catch (Exception e) {
            System.err.println("❌ Error al decodificar Base64: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método de utilidad para obtener XML sellado con validaciones
     */
    public String obtenerXmlSelladoValidado(String cufe, String nitEmpresa) {
        if (cufe == null || cufe.trim().isEmpty()) {
            System.err.println("❌ CUFE no puede estar vacío");
            return null;
        }

        if (nitEmpresa == null || nitEmpresa.trim().isEmpty()) {
            System.err.println("❌ NIT de empresa no puede estar vacío");
            return null;
        }

        return obtenerXmlSellado(cufe.trim(), nitEmpresa.trim());
    }

    /**
     * Actualizar token JWT manualmente si es necesario
     */
    public void actualizarToken(String nuevoToken) {
        this.jwtToken = nuevoToken;
        System.out.println("🔑 Token JWT actualizado");
    }

    /**
     * Obtener el token actual (para debugging)
     */
    public String getTokenActual() {
        return jwtToken;
    }
}