package com.searchitemsapp.dto;

import java.time.LocalDate;

import com.searchitemsapp.model.TbSiaEmpresa;
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
public class SelectoresCssDTO  implements IFdto {
	
	/*
	 * Variables Globales
	 */
	private Integer did;
	private Boolean bolActivo;
	private LocalDate fecModificacion;
	private String scrapNoPattern;
	private String scrapPattern;
	private String selImage;
	private String selLinkProd;
	private String selPreKilo;
	private String selPrecio;
	private String selProducto;
	private String selPaginacion;
	private TbSiaEmpresa tbSiaEmpresa;
	private TbSiaUrl tbSiaUrl;

	/*
	 * Constructor
	 */
	public SelectoresCssDTO() {
		super();
		tbSiaEmpresa = new TbSiaEmpresa();
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

	public Boolean getBolActivo() {
		return bolActivo;
	}

	public void setBolActivo(Boolean bolActivo) {
		this.bolActivo = bolActivo;
	}

	public LocalDate getFecModificacion() {
		return fecModificacion;
	}

	public void setFecModificacion(LocalDate fecModificacion) {
		this.fecModificacion = fecModificacion;
	}

	public String getScrapNoPattern() {
		return scrapNoPattern;
	}

	public void setScrapNoPattern(String scrapNoPattern) {
		this.scrapNoPattern = scrapNoPattern;
	}

	public String getScrapPattern() {
		return scrapPattern;
	}

	public void setScrapPattern(String scrapPattern) {
		this.scrapPattern = scrapPattern;
	}

	public String getSelImage() {
		return selImage;
	}

	public void setSelImage(String selImage) {
		this.selImage = selImage;
	}

	public String getSelLinkProd() {
		return selLinkProd;
	}

	public void setSelLinkProd(String selLinkProd) {
		this.selLinkProd = selLinkProd;
	}

	public String getSelPreKilo() {
		return selPreKilo;
	}

	public void setSelPreKilo(String selPreKilo) {
		this.selPreKilo = selPreKilo;
	}

	public String getSelPrecio() {
		return selPrecio;
	}

	public void setSelPrecio(String selPrecio) {
		this.selPrecio = selPrecio;
	}

	public String getSelProducto() {
		return selProducto;
	}

	public void setSelProducto(String selProducto) {
		this.selProducto = selProducto;
	}

	public TbSiaEmpresa getTbSiaEmpresa() {
		return tbSiaEmpresa;
	}

	public void setTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa) {
		this.tbSiaEmpresa = tbSiaEmpresa;
	}

	public TbSiaUrl getTbSiaUrl() {
		return tbSiaUrl;
	}

	public void setTbSiaUrl(TbSiaUrl tbSiaUrl) {
		this.tbSiaUrl = tbSiaUrl;
	}

	public String getSelPaginacion() {
		return selPaginacion;
	}

	public void setSelPaginacion(String selPaginacion) {
		this.selPaginacion = selPaginacion;
	}

	/*
	 * Métodos sobre escritos
	 */
	@Override
	public String toString() {
		return "SelectoresCssDTO [did=" + did + ", bolActivo=" + bolActivo + ", fecModificacion=" + fecModificacion
				+ ", scrapNoPattern=" + scrapNoPattern + ", scrapPattern=" + scrapPattern + ", selImage=" + selImage
				+ ", selLinkProd=" + selLinkProd + ", selPreKilo=" + selPreKilo + ", selPrecio=" + selPrecio
				+ ", selProducto=" + selProducto + ", selPaginacion=" + selPaginacion + ", tbSiaEmpresa=" + tbSiaEmpresa
				+ ", tbSiaUrl=" + tbSiaUrl + "]";
	}
}
