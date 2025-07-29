package com.lsda.api.entities;

import java.math.BigDecimal;

/**
 *
 * @author TEST
 */
public class Cargo {

	private String llaveComprobante;
	private String razon;
	private String codCargo;
	private String porcentaje;
	private BigDecimal basecargo;
	private String total;

	public Cargo() {

	}

	public Cargo(String llaveComprobante, String razon, String codCargo, String porcentaje, BigDecimal basecargo,
			String total) {

		this.llaveComprobante = llaveComprobante;
		this.razon = razon;
		this.codCargo = codCargo;
		this.porcentaje = porcentaje;
		this.basecargo = basecargo;
		this.total = total;
	}

	public String getLlaveComprobante() {
		return llaveComprobante;
	}

	public void setLlaveComprobante(String llaveComprobante) {
		this.llaveComprobante = llaveComprobante;
	}

	public String getrazon() {
		return razon;
	}

	public void setrazon(String razon) {
		this.razon = razon;
	}

	public String getCodCargo() {
		return codCargo;
	}

	public void setCodCargo(String codCargo) {
		this.codCargo = codCargo;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public BigDecimal getBasecargo() {
		return basecargo;
	}

	public void setBasecargo(BigDecimal basecargo) {
		this.basecargo = basecargo;
	}

	public BigDecimal getTotal() {
		return new BigDecimal(total);
	}

	public void setTotal(String total) {
		this.total = total;
	}

}