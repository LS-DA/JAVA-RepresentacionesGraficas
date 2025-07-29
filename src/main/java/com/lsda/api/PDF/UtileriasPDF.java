/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsda.api.PDF;

/**
 *
 * @author TEST
 */


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xml.sax.SAXException;

public class UtileriasPDF {
    
    
    public String cargarFEchaValida(String xml) throws ParserConfigurationException, SAXException, IOException, Exception {

        String fechavalida = "";
        String horavalida = "";

        JSONParser parser2 = new JSONParser();

        
        
        try {
            JSONObject jsonDian2 = (JSONObject) parser2.parse(xml);
            xml = decode(jsonDian2.get("Xml").toString()).toString();

            fechavalida = xml.substring(xml.indexOf("<cbc:IssueDate>") + 15, xml.indexOf("<cbc:IssueDate>")+25);
            horavalida = xml.substring(xml.indexOf("<cbc:IssueTime>") + 15, xml.indexOf("<cbc:IssueTime>")+29);
            fechavalida = "Fecha y hora de validacion DIAN " + fechavalida + " " + horavalida;

/*
            fechavalida = xml.substring(xml.indexOf("<cbc:IssueDate>") + 15, 9800);
            horavalida = xml.substring(xml.indexOf("<cbc:IssueTime>") + 15, 9849);
            fechavalida="Fecha y hora de validacion ante la DIAN " + fechavalida  + " " + horavalida;
*/

        } catch (org.json.simple.parser.ParseException ex) {
            //Logger.getLogger(facPDF.class.getName()).log(Level.SEVERE, null, ex);
            fechavalida="Fecha y hora de validacion DIAN ";
        }

        return fechavalida;

    }
    
    
      public static String encode(String value) throws Exception {
        //byte[] decodedValue = Base64.getDecoder().decode(value);
        byte[] encodeValue = java.util.Base64.getEncoder().encode(value.getBytes("UTF-8"));

        //System.out.println("encode" + encodeValue);// Basic Base64 decoding
        return new String(encodeValue, StandardCharsets.ISO_8859_1);
    }

    public static String decode(String value) throws Exception {
        byte[] decodedValue = java.util.Base64.getDecoder().decode(value);
        //System.out.println("decode" + decodedValue);// Basic Base64 decoding
        return new String(decodedValue, StandardCharsets.ISO_8859_1);
    }

    
}
