package edu.pe.idat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cargo_empleado")
public class CargoEmpleado {
	
	@Id
	private Integer codcargo;

	@Column
	private String cargo;

	public Integer getCodcargo() {
		return codcargo;
	}

	public void setCodcargo(Integer codcargo) {
		this.codcargo = codcargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public CargoEmpleado(Integer codcargo, String cargo) {
		super();
		this.codcargo = codcargo;
		this.cargo = cargo;
	}

	public CargoEmpleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
