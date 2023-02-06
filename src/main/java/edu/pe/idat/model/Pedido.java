package edu.pe.idat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
	private int codpedido;
	
	@Column(name ="fechacreacion")
	private Date fechacreacion;
	
	
	
	@Column(name ="direccion")
	private String direccion;
	
	/*@Column(name ="subtotal")
	private double subtotal;
	
	@Column(name ="igv")
	private double igv;*/
	
	@Column(name ="monto")
	private double monto;
	
	/*@Column(name ="estado")
	private String estado;*/
	@Column(name ="estadopedido")
	private String estado;
	
	
	@Column(name ="codcliente")
	private int codcliente;
	
	@Column(name ="codenvio")
	private int codenvio;
	

	
	public int getCodpedido() {
		return codpedido;
	}
	public void setCodpedido(int codpedido) {
		this.codpedido = codpedido;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public int getCodcliente() {
		return codcliente;
	}
	public void setCodcliente(int codcliente) {
		this.codcliente = codcliente;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/*public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getIgv() {
		return igv;
	}
	public void setIgv(double igv) {
		this.igv = igv;
	}*/
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	//AGREGADO
	public int getCodenvio() {
		return codenvio;
	}
	public void setCodenvio(int codenvio) {
		this.codenvio = codenvio;
	}
	
	
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*public Pedido(int codpedido, Date fechacreacion, int codcliente, String direccion, double subtotal, double igv,
			double monto, String estado) {
		super();
		this.codpedido = codpedido;
		this.fechacreacion = fechacreacion;
		this.codcliente = codcliente;
		this.direccion = direccion;
		this.subtotal = subtotal;
		this.igv = igv;
		this.monto = monto;
		this.estado = estado;
	}*/
	public Pedido(int codpedido, Date fechacreacion, String direccion, double monto, String estado, int codcliente,
			int codenvio) {
		super();
		this.codpedido = codpedido;
		this.fechacreacion = fechacreacion;
		this.direccion = direccion;
		this.monto = monto;
		this.estado = estado;
		this.codcliente = codcliente;
		this.codenvio = codenvio;
	}
	
	
	
}




