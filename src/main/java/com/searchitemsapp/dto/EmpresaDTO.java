package com.searchitemsapp.dto;

import java.util.List;

import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaPais;
import com.searchitemsapp.model.TbSiaSelectoresCss;
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
public class EmpresaDTO implements IFdto {

	/*
	 * Variables
	 */
	private Integer did;
	private Boolean bolActivo;
	private String desEmpresa;
	private String nomEmpresa;
	private TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa;
	private TbSiaPais tbSiaPais;
	private List<TbSiaUrl> tbSiaUrls;
	private Boolean bolDynScrap;	
	private List<TbSiaSelectoresCss> tbSiaSelectoresCsses;	
	
	/*
	 * Constructor
	 */
	public EmpresaDTO() {
		super();
		tbSiaCategoriasEmpresa = new TbSiaCategoriasEmpresa();
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

	public String getDesEmpresa() {
		return desEmpresa;
	}

	public void setDesEmpresa(String desEmpresa) {
		this.desEmpresa = desEmpresa;
	}

	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}

	public TbSiaCategoriasEmpresa getTbSiaCategoriasEmpresa() {
		return tbSiaCategoriasEmpresa;
	}

	public void setTbSiaCategoriasEmpresa(TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa) {
		this.tbSiaCategoriasEmpresa = tbSiaCategoriasEmpresa;
	}

	public TbSiaPais getTbSiaPais() {
		return tbSiaPais;
	}

	public void setTbSiaPais(TbSiaPais tbSiaPais) {
		this.tbSiaPais = tbSiaPais;
	}

	public List<TbSiaUrl> getTbSiaUrls() {
		return tbSiaUrls;
	}

	public void setTbSiaUrls(List<TbSiaUrl> tbSiaUrls) {
		this.tbSiaUrls = tbSiaUrls;
	}
	
	public Boolean getBolDynScrap() {
		return bolDynScrap;
	}

	public void setBolDynScrap(Boolean bolDynScrap) {
		this.bolDynScrap = bolDynScrap;
	}

	public List<TbSiaSelectoresCss> getTbSiaSelectoresCsses() {
		return tbSiaSelectoresCsses;
	}

	public void setTbSiaSelectoresCsses(List<TbSiaSelectoresCss> tbSiaSelectoresCsses) {
		this.tbSiaSelectoresCsses = tbSiaSelectoresCsses;
	}

	/*
	 * Métodos sobre escitos 
	 */
	@Override
	public String toString() {
		return "EmpresaDTO [did=" + did + ", bolActivo=" + bolActivo + ", desEmpresa=" + desEmpresa + ", nomEmpresa="
				+ nomEmpresa + ", tbSiaCategoriasEmpresa=" + tbSiaCategoriasEmpresa + ", tbSiaPais=" + tbSiaPais
				+ ", tbSiaUrls=" + tbSiaUrls + ", bolDynScrap=" + bolDynScrap + ", tbSiaSelectoresCsses="
				+ tbSiaSelectoresCsses + "]";
	}
}
