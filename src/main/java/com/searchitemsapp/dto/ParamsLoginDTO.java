package com.searchitemsapp.dto;

import com.searchitemsapp.model.TbSiaUrl;

/**
 * Objeto de Transferencia de Datos (DTO) 
 * es un objeto que transporta datos entre procesos.
 * No tiene más comportamiento que almacenar y entregar 
 * sus propios datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ParamsLoginDTO implements IFdto {

	/*
	 * Variables Globales
	 */
	private Integer did;
	private String paramClave;
	private String paramValor;
	private TbSiaUrl tbSiaUrl;
	private Boolean bolActivo;
	
	/*
	 * Constructor
	 */
	public ParamsLoginDTO() {
		super();
		tbSiaUrl = new TbSiaUrl();
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
	public TbSiaUrl getTbSiaUrl() {
		return tbSiaUrl;
	}
	public void setTbSiaUrl(TbSiaUrl tbSiaUrl) {
		this.tbSiaUrl = tbSiaUrl;
	}
	public Boolean getBolActivo() {
		return bolActivo;
	}
	public void setBolActivo(Boolean bolActivo) {
		this.bolActivo = bolActivo;
	}
	
	/*
	 * Métodos sobre-escritos
	 */
	@Override
	public String toString() {
		return "ParamsLoginDTO [did=" + did + ", paramClave=" + paramClave + ", paramValor=" + paramValor
				+ ", tbSiaUrl=" + tbSiaUrl + ", bolActivo=" + bolActivo + "]";
	}
}
