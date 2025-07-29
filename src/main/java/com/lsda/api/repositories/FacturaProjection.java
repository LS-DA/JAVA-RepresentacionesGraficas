/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lsda.api.repositories;

/**
 *
 * @author jjimenez
 */
public interface FacturaProjection {
    Long getId();
    String getCufe();
    String getAppres();
    String getBase64doc();
    String getXml();  // Note: your entity uses getxml()—fix casing if needed for consistency
    String getQrdata();
}
