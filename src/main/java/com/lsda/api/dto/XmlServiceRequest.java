package com.lsda.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XmlServiceRequest {
    
    @JsonProperty("trackid")
    private String trackid;
    
    @JsonProperty("nit")
    private String nit;
    
    @JsonProperty("passCert")
    private String passCert;

    // Constructor por defecto
    public XmlServiceRequest() {
    }

    // Constructor completo
    public XmlServiceRequest(String trackid, String nit, String passCert) {
        this.trackid = trackid;
        this.nit = nit;
        this.passCert = passCert;
    }

    // Getters y Setters
    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPassCert() {
        return passCert;
    }

    public void setPassCert(String passCert) {
        this.passCert = passCert;
    }

    @Override
    public String toString() {
        return "XmlServiceRequest{" +
                "trackid='" + trackid + '\'' +
                ", nit='" + nit + '\'' +
                ", passCert='" + passCert + '\'' +
                '}';
    }
}