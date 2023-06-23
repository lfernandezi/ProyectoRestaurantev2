package edu.pe.idat.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto {
	
	@Id
	private int codproducto;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "precio")
	private double precio;
	
	@Column(name = "codcategoria")
	private int codcategoria;
	
	@Column(name = "imagen")
	private String imagen;

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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCodcategoria() {
		return codcategoria;
	}

	public void setCodcategoria(int codcategoria) {
		this.codcategoria = codcategoria;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Producto(int codproducto, String nombre, String descripcion, double precio, int codcategoria,
			String imagen) {
		super();
		this.codproducto = codproducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.codcategoria = codcategoria;
		this.imagen = imagen;
	}

	public Producto() {
		
	}

	
	
	
}
