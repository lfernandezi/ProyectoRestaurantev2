package com.example.ProyectoRestaurantev2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="direccion")
public class Direccion {

	@Id
	private int coddireccion;
	
	@Column(name = "direccion")
	private String xdireccion;
	
	@Column(name = "distrito")
	private String xdistrito;
	
	@Column(name = "provincia")
	private String xprovincia;
	
	@Column(name = "referencia")
	private String xreferencia;
	
	@Column(name = "latitud")
	private String xlatitud;
	
	
	@Column(name = "longitud")
	private String xlongitud;
	
	@Column(name = "codcliente")
	private String xcodcliente;

	public int getCoddireccion() {
		return coddireccion;
	}

	public void setCoddireccion(int coddireccion) {
		this.coddireccion = coddireccion;
	}

	public String getXdireccion() {
		return xdireccion;
	}

	public void setXdireccion(String xdireccion) {
		this.xdireccion = xdireccion;
	}

	public String getXdistrito() {
		return xdistrito;
	}

	public void setXdistrito(String xdistrito) {
		this.xdistrito = xdistrito;
	}

	public String getXprovincia() {
		return xprovincia;
	}

	public void setXprovincia(String xprovincia) {
		this.xprovincia = xprovincia;
	}

	public String getXreferencia() {
		return xreferencia;
	}

	public void setXreferencia(String xreferencia) {
		this.xreferencia = xreferencia;
	}

	public String getXlatitud() {
		return xlatitud;
	}

	public void setXlatitud(String xlatitud) {
		this.xlatitud = xlatitud;
	}

	public String getXlongitud() {
		return xlongitud;
	}

	public void setXlongitud(String xlongitud) {
		this.xlongitud = xlongitud;
	}

	public String getXcodcliente() {
		return xcodcliente;
	}

	public void setXcodcliente(String xcodcliente) {
		this.xcodcliente = xcodcliente;
	}

	public Direccion(int coddireccion, String xdireccion, String xdistrito, String xprovincia, String xreferencia,
			String xlatitud, String xlongitud, String xcodcliente) {
		super();
		this.coddireccion = coddireccion;
		this.xdireccion = xdireccion;
		this.xdistrito = xdistrito;
		this.xprovincia = xprovincia;
		this.xreferencia = xreferencia;
		this.xlatitud = xlatitud;
		this.xlongitud = xlongitud;
		this.xcodcliente = xcodcliente;
	}

	public Direccion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
