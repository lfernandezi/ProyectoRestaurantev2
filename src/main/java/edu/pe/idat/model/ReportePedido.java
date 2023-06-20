package edu.pe.idat.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportePedido {
	
	@Id
	private int codpedido;
	
	private String direccion;
	private int codcliente;
	private String cliente;
	private String fechacreacion;
	private Double monto;
	private String  estadopedido;
	private int codproducto;
	private String nombreproducto;
	private int cantidad;
	private Double precio;
	private Double subtotal;
	
	public ReportePedido(int codpedido, String direccion, String cliente, String fechacreacion, Double monto,
			String estadopedido, int codproducto, String nombreproducto, int cantidad, Double precio, Double subtotal) {
		super();
		this.codpedido = codpedido;
		this.direccion = direccion;
		this.cliente = cliente;
		this.fechacreacion = fechacreacion;
		this.monto = monto;
		this.estadopedido = estadopedido;
		this.codproducto = codproducto;
		this.nombreproducto = nombreproducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.subtotal = subtotal;
	}
	
	

	public int getCodcliente() {
		return codcliente;
	}



	public void setCodcliente(int codcliente) {
		this.codcliente = codcliente;
	}



	public ReportePedido(int codpedido, String direccion, int codcliente, String cliente, String fechacreacion,
			Double monto, String estadopedido, int codproducto, String nombreproducto, int cantidad, Double precio,
			Double subtotal) {
		super();
		this.codpedido = codpedido;
		this.direccion = direccion;
		this.codcliente = codcliente;
		this.cliente = cliente;
		this.fechacreacion = fechacreacion;
		this.monto = monto;
		this.estadopedido = estadopedido;
		this.codproducto = codproducto;
		this.nombreproducto = nombreproducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.subtotal = subtotal;
	}



	public int getCodpedido() {
		return codpedido;
	}

	public void setCodpedido(int codpedido) {
		this.codpedido = codpedido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(String fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getEstadopedido() {
		return estadopedido;
	}

	public void setEstadopedido(String estadopedido) {
		this.estadopedido = estadopedido;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public ReportePedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
