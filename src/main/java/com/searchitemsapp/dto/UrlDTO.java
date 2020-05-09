package com.searchitemsapp.dto;

import java.util.List;

import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaSelectoresCss;

/**
 * Objeto de Transferencia de Datos (DTO) 
 * es un objeto que transporta datos entre procesos.
 * No tiene más comportamiento que almacenar y entregar 
 * sus propios datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class UrlDTO implements IFdto {

	/*
	 * Variables Globales
	 */
	private Integer did;
	private Boolean bolActivo;
	private String desUrl;
	private String nomUrl;
	private TbSiaEmpresa tbSiaEmpresa;
	private Boolean bolStatus;	
	private Boolean bolLogin;	
	private List<TbSiaSelectoresCss> tbSiaSelectoresCsses;
	
	/*
	 * Constructor
	 */
	public UrlDTO() {
		super();
		tbSiaEmpresa = new TbSiaEmpresa();
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

	public String getDesUrl() {
		return desUrl;
	}

	public void setDesUrl(String desUrl) {
		this.desUrl = desUrl;
	}

	public String getNomUrl() {
		return nomUrl;
	}

	public void setNomUrl(String nomUrl) {
		this.nomUrl = nomUrl;
	}

	public TbSiaEmpresa getTbSiaEmpresa() {
		return tbSiaEmpresa;
	}

	public void setTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa) {
		this.tbSiaEmpresa = tbSiaEmpresa;
	}

	public Boolean getBolStatus() {
		return this.bolStatus;
	}

	public void setBolStatus(Boolean bolStatus) {
		this.bolStatus = bolStatus;
	}
	
	public Boolean getBolLogin() {
		return bolLogin;
	}

	public void setBolLogin(Boolean bolLogin) {
		this.bolLogin = bolLogin;
	}
	
	public List<TbSiaSelectoresCss> getTbSiaSelectoresCsses() {
		return tbSiaSelectoresCsses;
	}

	public void setTbSiaSelectoresCsses(List<TbSiaSelectoresCss> tbSiaSelectoresCsses) {
		this.tbSiaSelectoresCsses = tbSiaSelectoresCsses;
	}

	/*
	 * Metodos sobre escritos
	 */
	@Override
	public String toString() {
		return "UrlDTO [did=" + did + ", bolActivo=" + bolActivo + ", desUrl=" + desUrl + ", nomUrl=" + nomUrl
				+ ", tbSiaEmpresa=" + tbSiaEmpresa + ", bolStatus=" + bolStatus + ", bolLogin=" + bolLogin
				+ ", tbSiaSelectoresCsses=" + tbSiaSelectoresCsses + "]";
	}
}
