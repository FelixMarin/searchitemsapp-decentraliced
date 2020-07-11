package com.searchitemsapp.dto;

import org.springframework.stereotype.Component;

/**
 * Objeto de Transferencia de Datos (DTO) 
 * es un objeto que transporta datos entre procesos.
 * No tiene más comportamiento que almacenar y entregar 
 * sus propios datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
@Component
public class ParamsLoginDTO implements IFdto {

	/*
	 * Variables Globales
	 */
	private Integer did;
	private String paramClave;
	private String paramValor;
	private Boolean bolActivo;
	private Integer didUrl;
	private String nomUrl;
	
	/*
	 * Constructor
	 */
	public ParamsLoginDTO() {
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
	public String getParamClave() {
		return paramClave;
	}
	public void setParamClave(String paramClave) {
		this.paramClave = paramClave;
	}
	public String getParamValor() {
		return paramValor;
	}
	public void setParamValor(String paramValor) {
		this.paramValor = paramValor;
	}
	public Boolean getBolActivo() {
		return bolActivo;
	}
	public void setBolActivo(Boolean bolActivo) {
		this.bolActivo = bolActivo;
	}
	
	public Integer getDidUrl() {
		return didUrl;
	}

	public void setDidUrl(Integer didUrl) {
		this.didUrl = didUrl;
	}

	public String getNomUrl() {
		return nomUrl;
	}

	public void setNomUrl(String nomUrl) {
		this.nomUrl = nomUrl;
	}

	/*
	 * Métodos sobre-escritos
	 */
	@Override
	public String toString() {
		return "ParamsLoginDTO [did=" + did + ", paramClave=" + paramClave + ", paramValor=" + paramValor
				+ ", bolActivo=" + bolActivo + "]";
	}
}
