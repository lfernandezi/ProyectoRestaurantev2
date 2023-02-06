package com.example.ProyectoRestaurantev2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="usuario")
public class Usuario {
	
	@Id
	private int codusuario;
	
	@Column(name = "email")
	private String xemail;
	
	@Column(name = "password")
	private String xpassword;
	
	@Column(name = "rol")
	private String xrol;

	public Usuario(int codusuario, String xemail, String xpassword, String xrol) {
		super();
		this.codusuario = codusuario;
		this.xemail = xemail;
		this.xpassword = xpassword;
		this.xrol = xrol;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCodusuario() {
		return codusuario;
	}

	public void setCodusuario(int codusuario) {
		this.codusuario = codusuario;
	}

	public String getXemail() {
		return xemail;
	}

	public void setXemail(String xemail) {
		this.xemail = xemail;
	}

	public String getXpassword() {
		return xpassword;
	}

	public void setXpassword(String xpassword) {
		this.xpassword = xpassword;
	}

	public String getXrol() {
		return xrol;
	}

	public void setXrol(String xrol) {
		this.xrol = xrol;
	}
	
	
	
	
	
	

}
