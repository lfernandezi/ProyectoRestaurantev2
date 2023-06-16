package edu.pe.idat.model.response;

import edu.pe.idat.model.Categoria;

public class ProductoResponse {
	
	
	private int codproducto;
	
	private String nombre;

	private String descripcion;
	
	private Double precio;
	
	private Categoria codcategoria;

	public int getCodproducto() {
		return codproducto;
	}

	public void setCodproducto(int codproducto) {
		this.codproducto = codproducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Categoria getCodcategoria() {
		return codcategoria;
	}

	public void setCodcategoria(Categoria codcategoria) {
		this.codcategoria = codcategoria;
	}

	public ProductoResponse(int codproducto, String nombre, String descripcion, Double precio,
			Categoria codcategoria) {
		super();
		this.codproducto = codproducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.codcategoria = codcategoria;
	}

	public ProductoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
