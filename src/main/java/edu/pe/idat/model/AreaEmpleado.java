package edu.pe.idat.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AreaEmpleado {
	
	@Id
	private Integer codarea;
	
	private String area;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public AreaEmpleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCodarea() {
		return codarea;
	}

	public void setCodarea(Integer codarea) {
		this.codarea = codarea;
	}

	public AreaEmpleado(Integer codarea, String area) {
		super();
		this.codarea = codarea;
		this.area = area;
	}

	
	
}
