package edu.pe.idat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
	@Id
	private Integer codcliente;
	
	@Column(name="email")
	private String usuario;
	
	//@Column(name="contraseña")
	//private String contraseña;
	
	@Column(name="contrasenia")
	private String contraseña;
	
	public Integer getCodcliente() {
		return codcliente;
	}
	public void setCodcliente(Integer codcliente) {
		this.codcliente = codcliente;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}	
}
