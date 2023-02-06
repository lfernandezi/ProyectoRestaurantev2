package edu.pe.idat.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empleado")
public class Empleado {
	
	@Id
	private int codempleado;	
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="dni")
	private String dni;
	
	@Column(name="contrasenia")
	private String contrasenia;
	
	@Column(name="fecha_ingreso")
	private Date fecha_ingreso;
	
	@Column(name="codcargo")
	private Integer codcargo;
	
	@Column(name="codarea")
	private Integer codarea;

	public int getCodempleado() {
		return codempleado;
	}

	public void setCodempleado(int codempleado) {
		this.codempleado = codempleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public Integer getCodcargo() {
		return codcargo;
	}

	public void setCodcargo(Integer codcargo) {
		this.codcargo = codcargo;
	}

	public Integer getCodarea() {
		return codarea;
	}

	public void setCodarea(Integer codarea) {
		this.codarea = codarea;
	}

	public Empleado(int codempleado, String nombre, String apellido, String dni, String contrasenia, Date fecha_ingreso,
			Integer codcargo, Integer codarea) {
		super();
		this.codempleado = codempleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrasenia = contrasenia;
		this.fecha_ingreso = fecha_ingreso;
		this.codcargo = codcargo;
		this.codarea = codarea;
	}

	public Empleado(int codempleado, String nombre, String apellido, String dni, String contrasenia,
			Date fecha_ingreso) {
		super();
		this.codempleado = codempleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrasenia = contrasenia;
		this.fecha_ingreso = fecha_ingreso;
	}

	public Empleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
