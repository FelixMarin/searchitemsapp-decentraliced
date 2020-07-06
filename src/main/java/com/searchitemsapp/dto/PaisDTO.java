package com.searchitemsapp.dto;

import java.util.LinkedHashMap;
import java.util.Objects;



/**
 * Objeto de Transferencia de Datos (DTO) 
 * es un objeto que transporta datos entre procesos.
 * No tiene más comportamiento que almacenar y entregar 
 * sus propios datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class PaisDTO implements IFdto {
	
	/*
	 * Variables Globales
	 */
	private Integer did;
	private Boolean bolActivo;
	private String desPais;
	private String nomPais;
	
	private LinkedHashMap<Integer, String> empresas;
	private LinkedHashMap<Integer, String> marcas;
	private LinkedHashMap<Integer, String> productos;
	
	/*
	 * Constructor
	 */
	public PaisDTO() {	
		super();
	}

	/*
	 * Métodos Getters y Setters
	 */
	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public Boolean getBolActivo() {
		return bolActivo;
	}

	public void setBolActivo(Boolean bolActivo) {
		this.bolActivo = bolActivo;
	}

	public String getDesPais() {
		return desPais;
	}

	public void setDesPais(String desPais) {
		this.desPais = desPais;
	}

	public String getNomPais() {
		return nomPais;
	}

	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}
	
	public LinkedHashMap<Integer, String> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(LinkedHashMap<Integer, String> empresas) {
		this.empresas = empresas;
	}

	public LinkedHashMap<Integer, String> getMarcas() {
		return marcas;
	}

	public void setMarcas(LinkedHashMap<Integer, String> marcas) {
		this.marcas = marcas;
	}

	public LinkedHashMap<Integer, String> getProductos() {
		return productos;
	}

	public void setProductos(LinkedHashMap<Integer, String> productos) {
		this.productos = productos;
	}

	/*
	 * Métodos sobre-escritos
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Objects.isNull(bolActivo)) ? 0 : bolActivo.hashCode());
		result = prime * result + ((Objects.isNull(desPais)) ? 0 : desPais.hashCode());
		result = prime * result + ((Objects.isNull(did)) ? 0 : did.hashCode());
		result = prime * result + ((Objects.isNull(nomPais)) ? 0 : nomPais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}

	@Override
	public String toString() {
		return "PaisDTO [did=" + did + ", bolActivo=" + bolActivo + ", desPais=" + desPais + ", nomPais=" + nomPais
				 + "]";
	}
}
