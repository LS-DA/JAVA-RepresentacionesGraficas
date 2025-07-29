/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsda.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que define las caracteristicas y comportamientos de una empresa
 * @author David Monterosas
 * @version 1.1
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findById", query = "SELECT e FROM Empresa e WHERE e.id = :id"),
    @NamedQuery(name = "Empresa.findByNombre", query = "SELECT e FROM Empresa e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empresa.findByNit", query = "SELECT e FROM Empresa e WHERE e.nit = :nit"),
    @NamedQuery(name = "Empresa.findByDigVeri", query = "SELECT e FROM Empresa e WHERE e.digveri = :digveri"),
    @NamedQuery(name = "Empresa.findByCorreo", query = "SELECT e FROM Empresa e WHERE e.correo = :correo"),
    @NamedQuery(name = "Empresa.findByDireccion", query = "SELECT e FROM Empresa e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empresa.findByTelefono", query = "SELECT e FROM Empresa e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empresa.findByUrl", query = "SELECT e FROM Empresa e WHERE e.url = :url"),
    @NamedQuery(name = "Empresa.findByPais", query = "SELECT e FROM Empresa e WHERE e.pais = :pais"),
    @NamedQuery(name = "Empresa.findByDepartamento", query = "SELECT e FROM Empresa e WHERE e.departamento = :departamento"),
    @NamedQuery(name = "Empresa.findByCiudad", query = "SELECT e FROM Empresa e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "Empresa.findByPorImpuesto", query = "SELECT e FROM Empresa e WHERE e.porimpuesto = :porimpuesto"),
    @NamedQuery(name = "Empresa.findByNomImpuesto", query = "SELECT e FROM Empresa e WHERE e.nomimpuesto = :nomimpuesto"),
    @NamedQuery(name = "Empresa.findByMonedaSimbolo", query = "SELECT e FROM Empresa e WHERE e.monedasimbolo = :monedasimbolo"),
    @NamedQuery(name = "Empresa.findByMonedaNombre", query = "SELECT e FROM Empresa e WHERE e.monedanombre = :monedanombre"),
    @NamedQuery(name = "Empresa.findByRegimen", query = "SELECT e FROM Empresa e WHERE e.regimen = :regimen"),
    @NamedQuery(name = "Empresa.findBySsmtp", query = "SELECT e FROM Empresa e WHERE e.ssmtp = :ssmtp"),
    @NamedQuery(name = "Empresa.findByUsmtp", query = "SELECT e FROM Empresa e WHERE e.usmtp = :usmtp"),
    @NamedQuery(name = "Empresa.findByCsmtp", query = "SELECT e FROM Empresa e WHERE e.csmtp = :csmtp"),
    @NamedQuery(name = "Empresa.findByPsmtp", query = "SELECT e FROM Empresa e WHERE e.psmtp = :psmtp"),
    @NamedQuery(name = "Empresa.findByEnvia", query = "SELECT e FROM Empresa e WHERE e.envia = :envia"),
    @NamedQuery(name = "Empresa.findByLogo", query = "SELECT e FROM Empresa e WHERE e.logo = :logo"),
    @NamedQuery(name = "Empresa.findByResolucion", query = "SELECT e FROM Empresa e WHERE e.resolucion = :resolucion"),
    @NamedQuery(name = "Empresa.findByLeyenda", query = "SELECT e FROM Empresa e WHERE e.leyenda = :leyenda"),
    @NamedQuery(name = "Empresa.findByExtra1", query = "SELECT e FROM Empresa e WHERE e.extra1 = :extra1"),
    @NamedQuery(name = "Empresa.findByExtra2", query = "SELECT e FROM Empresa e WHERE e.extra2 = :extra2"),
    @NamedQuery(name = "Empresa.findByExtra3", query = "SELECT e FROM Empresa e WHERE e.extra3 = :extra3"),
    @NamedQuery(name = "Empresa.findByExtra4", query = "SELECT e FROM Empresa e WHERE e.extra4 = :extra4"),
    @NamedQuery(name = "Empresa.findByExtra5", query = "SELECT e FROM Empresa e WHERE e.extra5 = :extra5")})
public class Empresa implements Serializable {
    
    /*Atributos de la clase Empresa*/
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "nit")
    private String nit;
    @Size(max = 10)
    @Column(name = "Digveri")
    private String digveri;
    @Size(max = 50)
    @Column(name = "Correo")
    private String correo;
    @Size(max = 100)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "Telefono")
    private String telefono;
    @Size(max = 50)
    @Column(name = "Url")
    private String url;
    @Size(max = 50)
    @Column(name = "Pais")
    private String pais;
    @Size(max = 50)
    @Column(name = "Departamento")
    private String departamento;
    @Size(max = 50)
    @Column(name = "Ciudad")
    private String ciudad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Porimpuesto")
    private BigDecimal porimpuesto;
    @Size(max = 50)
    @Column(name = "Nomimpuesto")
    private String nomimpuesto;
    @Size(max = 3)
    @Column(name = "Monedasimbolo")
    private String monedasimbolo;
    @Size(max = 50)
    @Column(name = "Monedanombre")
    private String monedanombre;
    @Size(max = 50)
    @Column(name = "Regimen")
    private String regimen;
    @Size(max = 50)
    @Column(name = "SSMTP")
    private String ssmtp;
    @Size(max = 50)
    @Column(name = "USMTP")
    private String usmtp;
    @Size(max = 50)
    @Column(name = "CSMTP")
    private String csmtp;
    @Size(max = 50)
    @Column(name = "PSMTP")
    private String psmtp;
    @Column(name = "Envia")
    private Integer envia;
    @Size(max = 1500)
    @Column(name = "Logo")
    private String logo;
    @Size(max = 50)
    @Column(name = "Resolucion")
    private String resolucion;
    @Size(max = 255)
    @Column(name = "Leyenda")
    private String leyenda;
    @Size(max = 150)
    @Column(name = "Extra1")
    private String extra1;
    @Size(max = 50)
    @Column(name = "Extra2")
    private String extra2;
    @Size(max = 50)
    @Column(name = "Extra3")
    private String extra3;
    @Size(max = 50)
    @Column(name = "Extra4")
    private String extra4;
    @Size(max = 50)
    @Column(name = "Extra5")
    private String extra5;
    @Size(max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 50)
    @Column(name = "Municipio")
    private String municipio;
    @Size(max = 50)
    @Column(name = "Primernombre")
    private String primerNombre;
    @Size(max = 50)
    @Column(name = "Segundonombre")
    private String segundonombre;
    @Size(max = 100)
    @Column(name = "Apellidos")
    private String apellidos;
    @Column(name = "Enabled")
    @NotNull
    private Boolean enabled;
    @Column(name = "idpadre")
    private Integer idPadre;
    
    @Column(name = "pdf")
    private Boolean pdf;
    /**
     * Constructor vacio de empresa
     */
    public Empresa() {
    }

    /**
     * Constructor con parametros de grupo
     * @param id parametro que define el id del empresa
     */
    public Empresa(Integer id) {
        this.id = id;
    }

    /*Metodos Getters y Setters correspondiente a cada atributo de empresa*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDigveri() {
		return digveri;
	}

	public void setDigveri(String digveri) {
		this.digveri = digveri;
	}

	public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public BigDecimal getPorimpuesto() {
		return porimpuesto;
	}

	public void setPorimpuesto(BigDecimal porimpuesto) {
		this.porimpuesto = porimpuesto;
	}

	public String getNomimpuesto() {
		return nomimpuesto;
	}

	public void setNomimpuesto(String nomimpuesto) {
		this.nomimpuesto = nomimpuesto;
	}

	public String getMonedasimbolo() {
		return monedasimbolo;
	}

	public void setMonedasimbolo(String monedasimbolo) {
		this.monedasimbolo = monedasimbolo;
	}

	public String getMonedanombre() {
		return monedanombre;
	}

	public void setMonedanombre(String monedanombre) {
		this.monedanombre = monedanombre;
	}

	public String getSegundonombre() {
		return segundonombre;
	}

	public void setSegundonombre(String segundonombre) {
		this.segundonombre = segundonombre;
	}

	public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getSsmtp() {
        return ssmtp;
    }

    public void setSsmtp(String ssmtp) {
        this.ssmtp = ssmtp;
    }

    public String getUsmtp() {
        return usmtp;
    }

    public void setUsmtp(String usmtp) {
        this.usmtp = usmtp;
    }

    public String getCsmtp() {
        return csmtp;
    }

    public void setCsmtp(String csmtp) {
        this.csmtp = csmtp;
    }

    public String getPsmtp() {
        return psmtp;
    }

    public void setPsmtp(String psmtp) {
        this.psmtp = psmtp;
    }

    public Integer getEnvia() {
        return envia;
    }

    public void setEnvia(Integer envia) {
        this.envia = envia;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }

    public String getExtra5() {
        return extra5;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Boolean getPdf() {
        return pdf;
    }

    public void setPdf(Boolean pdf) {
        this.pdf = pdf;
    }
    
    

    public Integer getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}

	/**
     * Método para comparar el id empresa de una forma mas rapido
     * @return un numero entero dependiendo del resultado de la comparación
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

     /**
     * Método para comparar la igualdad del objeto empresa
     * @param object parametro que define el objeto a comparar su igualdad
     * @return si es igual o no
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    	

    public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
     * Método para imprimir el id de la empresa
     * @return un id de tipo cadena 
     */
    @Override
    public String toString() {
        return "wfactura.co.Entities.Empresa[ id=" + id + " ]";
    }

}
