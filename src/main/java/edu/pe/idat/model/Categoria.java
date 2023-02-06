package edu.pe.idat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	/*
	 * @Id private String categoria;
	 * 
	 * public String getCategoria() { return categoria; }
	 * 
	 * public void setCategoria(String categoria) { this.categoria = categoria; }
	 */
	@Id
	private int codcategoria;

	private String categoria;

	public int getCodcategoria() {
		return codcategoria;
	}

	public void setCodcategoria(int codcategoria) {
		this.codcategoria = codcategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Categoria(int codcategoria, String categoria) {
		super();
		this.codcategoria = codcategoria;
		this.categoria = categoria;
	}

	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

}
