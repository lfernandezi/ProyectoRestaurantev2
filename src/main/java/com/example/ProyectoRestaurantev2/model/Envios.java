package com.example.ProyectoRestaurantev2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="envios")
public class Envios {
	@Id
	private int codenvio;
	
	
	
	@Column(name="fecha_envio")
	private Date fecha_envio;
	
	@Column(name="estadoenvio")
	private String estadoenvio;

	@Column(name="codempleado")
	private int codempleado;
	
	public Envios() {}

	public Envios(int codenvio, Date fecha_envio, String estadoenvio, int codempleado) {
		super();
		this.codenvio = codenvio;
		this.fecha_envio = fecha_envio;
		this.estadoenvio = estadoenvio;
		this.codempleado = codempleado;
	}


	public int getCodenvio() {
		return codenvio;
	}

	public void setCodenvio(int codenvio) {
		this.codenvio = codenvio;
	}

	public int getCodempleado() {
		return codempleado;
	}

	public void setCodempleado(int codempleado) {
		this.codempleado = codempleado;
	}

	
	

	public Date getFecha_envio() {
		return fecha_envio;
	}

	public void setFecha_envio(Date fecha_envio) {
		this.fecha_envio = fecha_envio;
	}

	public String getEstadoenvio() {
		return estadoenvio;
	}

	public void setEstadoenvio(String estadoenvio) {
		this.estadoenvio = estadoenvio;
	}
	
	
	
}
