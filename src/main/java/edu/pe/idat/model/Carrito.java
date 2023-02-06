package edu.pe.idat.model;


public class Carrito {
	

	private int codproducto;

	private String nombreproducto;
	
	private String descripcion;

	private int cantidad;

	private double precio;

	private double subtotal;

	public Carrito(int codproducto, String nombreproducto, String descripcion, int cantidad, double precio,
			double subtotal) {
		super();
		this.codproducto = codproducto;
		this.nombreproducto = nombreproducto;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precio = precio;
		this.subtotal = subtotal;
	}

	public int getCodproducto() {
		return codproducto;
	}

	public void setCodproducto(int codproducto) {
		this.codproducto = codproducto;
	}

	public String getNombreproducto() {
		return nombreproducto;
	}

	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	

}
