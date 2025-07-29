package com.lsda.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XmlServiceResponse {
    
    @JsonProperty("Xml")
    private String xml;

    // Constructor por defecto
    public XmlServiceResponse() {
    }

    // Constructor
    public XmlServiceResponse(String xml) {
        this.xml = xml;
    }

    // Getters y Setters
    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    @Override
    public String toString() {
        return "XmlServiceResponse{" +
                "xml='" + (xml != null ? xml.substring(0, Math.min(100, xml.length())) + "..." : "null") + '\'' +
                '}';
    }
}