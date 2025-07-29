package com.lsda.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class facPDF {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cufe;

    private String pdf64;
    
   

    public facPDF( String cufe, String pdf64) {
       
        this.cufe = cufe;
        this.pdf64 = pdf64;
       
    }
    
    public facPDF(){
        
    }

   
    
 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public String getPdf64() {
        return pdf64;
    }

    public void setPdf64(String pdf64) {
        this.pdf64 = pdf64;
    }

    @Override
    public String toString() {
        return "facPDF [id=" + id + ", cufe=" + cufe + "]";
    }

}
