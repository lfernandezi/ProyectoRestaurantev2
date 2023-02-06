package edu.pe.idat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="usuario")
@Entity
public class Usuario {
	
	@Id
	private Integer codusuario;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String rol;
	

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	
	public Integer getCodusuario() {
		return codusuario;
	}

	public void setCodusuario(Integer codusuario) {
		this.codusuario = codusuario;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer codusuario, String email, String password, String rol) {
		super();
		this.codusuario = codusuario;
		this.email = email;
		this.password = password;
		this.rol = rol;
	}
	
	

}
