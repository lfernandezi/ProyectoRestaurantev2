package edu.pe.idat.model;

import java.sql.Date;


public class EmpleadoResponse {

	private Integer codempleado;

	private String nombre;

	private String apellido;

	private String dni;

	private String contrasenia;

	private Date fecha_ingreso;

	private CargoEmpleado codcargo;

	private AreaEmpleado codarea;
	
	private String rol;

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

	public CargoEmpleado getCodcargo() {
		return codcargo;
	}

	public void setCodcargo(CargoEmpleado codcargo) {
		this.codcargo = codcargo;
	}

	public AreaEmpleado getCodarea() {
		return codarea;
	}

	public void setCodarea(AreaEmpleado codarea) {
		this.codarea = codarea;
	}
	
	

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public void setCodempleado(Integer codempleado) {
		this.codempleado = codempleado;
	}



	public EmpleadoResponse(Integer codempleado, String nombre, String apellido, String dni, String contrasenia,
			Date fecha_ingreso, CargoEmpleado codcargo, AreaEmpleado codarea, String rol) {
		super();
		this.codempleado = codempleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrasenia = contrasenia;
		this.fecha_ingreso = fecha_ingreso;
		this.codcargo = codcargo;
		this.codarea = codarea;
		this.rol = rol;
	}

	public EmpleadoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}





	
}
