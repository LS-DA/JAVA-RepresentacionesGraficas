package com.lsda.api.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "Correo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Correo.findAll", query = "SELECT c FROM Correo c"),
    @NamedQuery(name = "Correo.findById", query = "SELECT c FROM Correo c WHERE c.id = :id"),
    @NamedQuery(name = "Correo.findByHost", query = "SELECT c FROM Correo c WHERE c.host = :host"),
    @NamedQuery(name = "Correo.findByPuerto", query = "SELECT c FROM Correo c WHERE c.puerto = :puerto"),
    @NamedQuery(name = "Correo.findByDireccion", query = "SELECT c FROM Correo c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Correo.findByUsuario", query = "SELECT c FROM Correo c WHERE  c.usuario = :usuario"),
    @NamedQuery(name = "Correo.findByPassword", query = "SELECT c FROM Correo c WHERE c.password = :password"),
    @NamedQuery(name = "Correo.findByIdEmpresa", query = "SELECT c FROM Correo c WHERE c.idEmpresa = :idEmpresa")})

public class Correo implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
	@Size(max = 150)
    @Column(name = "host")
    private String host;
    @Column(name = "puerto")
    private Integer puerto;
    @Size(max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 150)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 150)
    @Column(name = "password")
    private String password;
    @Column(name = "idempresa")
    private Integer idEmpresa;

    /**
     * Constructor vacio de impuesto
     */
    public Correo() {
    }

    /**
     * Constructor con parametros de grupo
     * @param id parametro que define el id del comprobante
     */
    public Correo(Integer id) {
        this.id = id;
    }

    /*Metodos Getters y Setters correspondiente a cada atributo de impuesto*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPuerto() {
		return puerto;
	}

	public void setPuerto(Integer puerto) {
		this.puerto = puerto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
}
