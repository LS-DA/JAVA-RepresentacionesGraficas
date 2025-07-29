/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lsda.api.entities;

/**
 *
 * @author jjimenez
 */
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "empresa")
public class EmpresaCa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "nit")
    private String nit;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "extra2")
    private String passcert;

    // Constructor por defecto
    public EmpresaCa() {
    }

    // Constructor completo
    public EmpresaCa(String nit, String nombre, String passcert) {
        this.nit = nit;
        this.nombre = nombre;
        this.passcert = passcert;
    }

    // Getters y Setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPasscert() {
        return passcert;
    }

    public void setPasscert(String passcert) {
        this.passcert = passcert;
    }

    @Override
    public String toString() {
        return "Empresa{"
                + "nit='" + nit + '\''
                + ", nombre='" + nombre + '\''
                + ", passcert='" + passcert + '\''
                + '}';
    }

}
