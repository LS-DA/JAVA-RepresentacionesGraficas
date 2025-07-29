/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsda.api.repositories;

import com.lsda.api.entities.Login;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author TEST
 */
public interface LoginRepository extends JpaRepository<Login,Long>{
    
    
    List<Login> getLoginByNitAndRole(String nit,String role);    
    List<Login> getLoginByNit(String nit);
    
}
