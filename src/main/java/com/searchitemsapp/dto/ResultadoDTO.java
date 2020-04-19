package com.searchitemsapp.dto;

import java.util.Comparator;

import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.processprice.ProcessPrice;

public class ResultadoDTO implements IFdto, Comparator<ResultadoDTO> {
	
	private int identificador;
	private String nomProducto;
	private String desProducto;
	private Integer didEmpresa;
	private String precioKilo;
	private String imagen;
	private String precio;
	private TbSiaEmpresa tbSiaEmpresa;
	private String nomUrl;
	private String loginUrl;
	private Integer didUrl;
	private boolean bolActivo;
	private boolean bolStatus;
	private Boolean bolLogin;	
	private String desUrl;
	private String nomUrlAllProducts;
	private int ordenacion;
	
	public ResultadoDTO() {
		super();
		tbSiaEmpresa = new TbSiaEmpresa();
	}
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getNomProducto() {
		return nomProducto;
	}
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}
	public String getDesProducto() {
		return desProducto;
	}
	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}
	public String getImagen() {
		return imagen;	
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public Integer getDidEmpresa() {
		return didEmpresa;
	}
	public void setDidEmpresa(Integer didEmpresa) {
		this.didEmpresa = didEmpresa;
	}
	public String getPrecioKilo() {
		return precioKilo;
	}
	public void setPrecioKilo(String precioKilo) {
		this.precioKilo = precioKilo;
	}
	public TbSiaEmpresa getTbSiaEmpresa() {
		return tbSiaEmpresa;
	}
	public void setTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa) {
		this.tbSiaEmpresa = tbSiaEmpresa;
	}
	public String getNomUrl() {
		return nomUrl;
	}
	public void setNomUrl(String nomUrl) {
		this.nomUrl = nomUrl;
	}
	public Integer getDidUrl() {
		return didUrl;
	}
	public void setDidUrl(Integer didUrl) {
		this.didUrl = didUrl;
	}
	public boolean isBolActivo() {
		return bolActivo;
	}
	public void setBolActivo(boolean bolActivo) {
		this.bolActivo = bolActivo;
	}
	public boolean isBolStatus() {
		return bolStatus;
	}
	public void setBolStatus(boolean bolStatus) {
		this.bolStatus = bolStatus;
	}
	public Boolean isBolLogin() {
		return bolLogin;
	}
	public void setBolLogin(Boolean bolLogin) {
		this.bolLogin = bolLogin;
	}
	public String getDesUrl() {
		return desUrl;
	}
	public void setDesUrl(String desUrl) {
		this.desUrl = desUrl;
	}
	public String getNomUrlAllProducts() {
		return nomUrlAllProducts;
	}
	public void setNomUrlAllProducts(String nomUrlAllProducts) {
		this.nomUrlAllProducts = nomUrlAllProducts;
	}
	public String getLoginnUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public int getOrdenacion() {
		return ordenacion;
	}
	public void setOrdenacion(int ordenacion) {
		this.ordenacion = ordenacion;
	}

	@Override
	public int compare(ResultadoDTO a, ResultadoDTO b) {
		return ProcessPrice.processPrice(a, b);
	}
	
	@Override
	public String toString() {
		return "ResultadoDTO [identificador=" + identificador + ", nomProducto=" + nomProducto + ", desProducto="
				+ desProducto + ", didEmpresa=" + didEmpresa + ", precioKilo=" + precioKilo + ", imagen=" + imagen
				+ ", precio=" + precio + ", tbSiaEmpresa=" + tbSiaEmpresa + ", nomUrl=" + nomUrl + ", loginUrl="
				+ loginUrl + ", didUrl=" + didUrl + ", bolActivo=" + bolActivo + ", bolStatus=" + bolStatus
				+ ", bolLogin=" + bolLogin + ", desUrl=" + desUrl + ", nomUrlAllProducts=" + nomUrlAllProducts
				+ ", ordenacion=" + ordenacion + "]";
	}
}
