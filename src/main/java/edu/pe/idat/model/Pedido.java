package edu.pe.idat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	private int codpedido;

	@Column(name = "fechacreacion")
	private Date fechacreacion;

	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "referencia")
	private String referencia;

	@Column(name = "ubicacion")
	private String ubicacion;

	@Column(name = "monto")
	private double monto;

	@Column(name = "estadopedido")
	private String estado;

	@Column(name = "motivo")
	private String motivo;

	
	@Column(name = "codenvio")
	private int codenvio;

	@Column(name = "codcliente")
	private int codcliente;
	
	
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public int getCodenvio() {
		return codenvio;
	}

	public void setCodenvio(int codenvio) {
		this.codenvio = codenvio;
	}

	
	public int getCodcliente() {
		return codcliente;
	}

	public void setCodcliente(int codcliente) {
		this.codcliente = codcliente;
	}

	
	public Pedido(int codpedido, Date fechacreacion, String direccion, String referencia, String ubicacion,
			double monto, String estado, String motivo, int codenvio, int codcliente) {
		super();
		this.codpedido = codpedido;
		this.fechacreacion = fechacreacion;
		this.direccion = direccion;
		this.referencia = referencia;
		this.ubicacion = ubicacion;
		this.monto = monto;
		this.estado = estado;
		this.motivo = motivo;
		this.codenvio = codenvio;
		this.codcliente = codcliente;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/*public Pedido(int codpedido, Date fechacreacion, String direccion, String ubicacion, double monto, String estado,
			String motivo, int codenvio, int codcliente) {
		super();
		this.codpedido = codpedido;
		this.fechacreacion = fechacreacion;
		this.direccion = direccion;
		this.ubicacion = ubicacion;
		this.monto = monto;
		this.estado = estado;
		this.motivo = motivo;
		this.codenvio = codenvio;
		this.codcliente = codcliente;
	}*/
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/*public Pedido(String direccion, String ubicacion, double monto, String estado, String motivo, int codenvio,
			int codcliente) {
		super();
		this.direccion = direccion;
		this.ubicacion = ubicacion;
		this.monto = monto;
		this.estado = estado;
		this.motivo = motivo;
		this.codenvio = codenvio;
		this.codcliente = codcliente;
	}
*/
	


}
