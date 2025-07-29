/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lsda.api.repositories;

import com.lsda.api.entities.Factura;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jjimenez
 */
@Mapper
public interface FacturaMapper {
    
    @Select("SELECT id, cufe, appres, base64doc, xml, qrdata " +
            "FROM factura WITH (NOLOCK) " +
            "WHERE cufe = #{cufe}")
    Factura getFacturaByCufe(@Param("cufe") String cufe);
}
