/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsda.api.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que define las caracteristicas y comportamientos de una resolución
 *
 * @author David Monterosas
 * @version 1.1
 */
@Entity
@Table(name = "resoluciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resoluciones.findAll", query = "SELECT r FROM Resoluciones r")
    ,
    @NamedQuery(name = "Resoluciones.findById", query = "SELECT r FROM Resoluciones r WHERE r.id = :id")
    ,
    @NamedQuery(name = "Resoluciones.findByConsecutivoActual", query = "SELECT r FROM Resoluciones r WHERE r.consecutivoactual = :consecutivoactual")
    ,
    @NamedQuery(name = "Resoluciones.findByNoResolucion", query = "SELECT r FROM Resoluciones r WHERE r.noresolucion = :noresolucion")
    ,
    @NamedQuery(name = "Resoluciones.findByFechaExpedicion", query = "SELECT r FROM Resoluciones r WHERE r.fechaexpedicion = :fechaexpedicion")
    ,
    @NamedQuery(name = "Resoluciones.findByFechaVencimiento", query = "SELECT r FROM Resoluciones r WHERE r.fechavencimiento = :fechavencimiento")
    ,
    @NamedQuery(name = "Resoluciones.findByPrefijo", query = "SELECT r FROM Resoluciones r WHERE r.prefijo = :prefijo")
    ,
    @NamedQuery(name = "Resoluciones.findByDesde", query = "SELECT r FROM Resoluciones r WHERE r.desde = :desde")
    ,
    @NamedQuery(name = "Resoluciones.findByHasta", query = "SELECT r FROM Resoluciones r WHERE r.hasta = :hasta")
    ,
    @NamedQuery(name = "Resoluciones.findByIdEmpresa", query = "SELECT r FROM Resoluciones r WHERE r.idempresa = :idempresa")
    ,
    @NamedQuery(name = "Resoluciones.findByIdUsuarios", query = "SELECT r FROM Resoluciones r WHERE r.idusuarios = :idusuarios")
    ,
    @NamedQuery(name = "Resoluciones.findByClaveTecnica", query = "SELECT r FROM Resoluciones r WHERE r.clavetecnica = :clavetecnica")})
public class Resoluciones implements Serializable {

    /*Atributos de la clase Resoluciones*/
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "consecutivoactual")
    private String consecutivoactual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "noresolucion")
    private String noresolucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaexpedicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaexpedicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavencimiento;
    @Size(max = 50)
    @Column(name = "prefijo")
    private String prefijo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "desde")
    private String desde;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "hasta")
    private String hasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idempresa")
    private int idempresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idusuarios")
    private int idusuarios;
    @Size(max = 250)
    @Column(name = "clavetecnica")
    private String clavetecnica;

    /**
     * Constructor vacio de resolucion
     */
    public Resoluciones() {
    }

    /**
     * Constructor con parametros de resolucion
     *
     * @param id parametro que define el id de la resolucion
     */
    public Resoluciones(Integer id) {
        this.id = id;
    }

    /**
     * Constructor con parametros de resolucion
     *
     * @param id parametro que define el id de la resolución
     * @param consecutivoActual parametro que define el consectuvo actual de la
     * resolucion
     * @param noResolucion parametro que define el numero de la resolucion
     * @param fechaExpedicion parametro que define la fecha de expedicion de la
     * resolucion
     * @param fechaVencimiento parametro que define la fecha de vencimiento de
     * la resolucion
     * @param desde parametro que define la fecha desde de la resolucion
     * @param hasta parametro que define la fecha hasta de la resolucion
     * @param idEmpresa parametro que define el id de la empresa
     * @param idUsuarios parametro que define el id de usuario
     */
    public Resoluciones(Integer id, String consecutivoactual, String noresolucion, Date fechaexpedicion, Date fechavencimiento, String desde, String hasta, int idempresa, int idusuarios) {
        this.id = id;
        this.consecutivoactual = consecutivoactual;
        this.noresolucion = noresolucion;
        this.fechaexpedicion = fechaexpedicion;
        this.fechavencimiento = fechavencimiento;
        this.desde = desde;
        this.hasta = hasta;
        this.idempresa = idempresa;
        this.idusuarios = idusuarios;
    }

    /*Metodos Getters y Setters correspondiente a cada atributo de resolucion*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsecutivoactual() {
        return consecutivoactual;
    }

    public void setConsecutivoactual(String consecutivoactual) {
        this.consecutivoactual = consecutivoactual;
    }

    public String getNoresolucion() {
        return noresolucion;
    }

    public void setNoresolucion(String noresolucion) {
        this.noresolucion = noresolucion;
    }

    public Date getFechaexpedicion() {
        return fechaexpedicion;
    }

    public void setFechaexpedicion(Date fechaexpedicion) {
        this.fechaexpedicion = fechaexpedicion;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }

    public String getClavetecnica() {
        return clavetecnica;
    }

    public void setClavetecnica(String clavetecnica) {
        this.clavetecnica = clavetecnica;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    /**
     * Método para comparar el id resolucion de una forma mas rapido
     *
     * @return un numero entero dependiendo del resultado de la comparación
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Método para comparar la igualdad del objeto resolucion
     *
     * @param object parametro que define el objeto a comparar su igualdad
     * @return si es igual o no
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resoluciones)) {
            return false;
        }
        Resoluciones other = (Resoluciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Método para imprimir el id de la resolucion
     *
     * @return un id de tipo cadena
     */
    @Override
    public String toString() {
        return "wfactura.co.Entities.Resoluciones[ id=" + id + " ]";
    }

}
