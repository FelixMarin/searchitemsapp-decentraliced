package com.searchitemsapp.dto;

public class ParametrosEntradaDTO {

	private String didPais;
	private String didCategoria; 
	private String ordenacion; 
	private String producto; 
	private String empresas;
	
	public ParametrosEntradaDTO() {
		super();
	}

	public String getDidPais() {
		return didPais;
	}

	public void setDidPais(String didPais) {
		this.didPais = didPais;
	}

	public String getDidCategoria() {
		return didCategoria;
	}

	public void setDidCategoria(String didCategoria) {
		this.didCategoria = didCategoria;
	}

	public String getOrdenacion() {
		return ordenacion;
	}

	public void setOrdenacion(String ordenacion) {
		this.ordenacion = ordenacion;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getEmpresas() {
		return empresas;
	}

	public void setEmpresas(String empresas) {
		this.empresas = empresas;
	}

	@Override
	public String toString() {
		return "ParametrosEntradaDTO [didPais=" + didPais + ", didCategoria=" + didCategoria + ", ordenacion="
				+ ordenacion + ", producto=" + producto + ", empresas=" + empresas + "]";
	}
}
