package com.lsda.api.entities;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "factura", indexes = {
    @Index(name = "IX_Factura_CUFE_Fecha", columnList = "cufe")
})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "xml", columnDefinition = "TEXT")
    private String xml;

    
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "base64doc", columnDefinition = "TEXT")
    private String base64doc;

     @Basic(fetch = FetchType.EAGER)
    @Column(name = "cufe", length = 255)
    private String cufe;

    @Column(name = "qrdata")
    private String qrdata;

    @Column(name = "appres")
    private String appres;
    
     @Column(name = "nitemisor")
    private String nitemisor;

    // Constructor por defecto
    public Factura() {
    }

    // Constructor para consulta nativa
    public Factura(Long id, String cufe, String appres, String base64doc, String xml, String qrdata, String nitemisor) {
        this.id = id;
        this.cufe = cufe;
        this.appres = appres;
        this.base64doc = base64doc;
        this.xml = xml;
        this.qrdata = qrdata;
        this.nitemisor= nitemisor;
    }

    public String getAppres() {
        return appres;
    }

    public void setAppres(String appres) {
        this.appres = appres;
    }

    public String getBase64doc() {
        return base64doc;
    }

    public void setBase64doc(String base64doc) {
        this.base64doc = base64doc;
    }

    public String getQrdata() {
        return qrdata;
    }

    public void setQrdata(String qrdata) {
        this.qrdata = qrdata;
    }

    public String getxml() {
        return xml;
    }

    public void setxml(String xml) {
        try {
            this.xml = (xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNitemisor() {
        return nitemisor;
    }

    public void setNitemisor(String nitemisor) {
        this.nitemisor = nitemisor;
    }
    
    
    

    public static String decode(String value) throws Exception {
        byte[] decodedValue = Base64.getDecoder().decode(value);
        return new String(decodedValue, StandardCharsets.ISO_8859_1);
    }

    public static String encode(String value) throws Exception {
        byte[] encodeValue = Base64.getEncoder().encode(value.getBytes("UTF-8"));
        return new String(encodeValue, StandardCharsets.ISO_8859_1);
    }
}