package com.searchitemsapp.dto;

import java.util.List;
import java.util.Objects;

import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaMarcas;
import com.searchitemsapp.model.TbSiaNomProducto;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.StringUtils;


public class CategoriaDTO implements IFdto {

	private Integer did;
	private Boolean bolActivo;
	private String desCatEmpresa;
	private String nomCatEmpresa;
	private List<TbSiaEmpresa> tbSiaEmpresas;
	private List<TbSiaMarcas> tbSiaMarcas;
	private List<TbSiaNomProducto> tbSiaNomProductos;
	
	public CategoriaDTO() {
		super();
	}

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

	public String getDesCatEmpresa() {
		return desCatEmpresa;
	}

	public void setDesCatEmpresa(String desCatEmpresa) {
		this.desCatEmpresa = desCatEmpresa;
	}

	public String getNomCatEmpresa() {
		return nomCatEmpresa;
	}

	public void setNomCatEmpresa(String nomCatEmpresa) {
		this.nomCatEmpresa = nomCatEmpresa;
	}

	public List<TbSiaEmpresa> getTbSiaEmpresas() {
		return tbSiaEmpresas;
	}

	public void setTbSiaEmpresas(List<TbSiaEmpresa> tbSiaEmpresas) {
		this.tbSiaEmpresas = tbSiaEmpresas;
	}
	
	public List<TbSiaMarcas> getTbSiaMarcas() {
		return tbSiaMarcas;
	}

	public void setTbSiaMarcas(List<TbSiaMarcas> tbSiaMarcas) {
		this.tbSiaMarcas = tbSiaMarcas;
	}

	public List<TbSiaNomProducto> getTbSiaNomProductos() {
		return tbSiaNomProductos;
	}

	public void setTbSiaNomProductos(List<TbSiaNomProducto> tbSiaNomProductos) {
		this.tbSiaNomProductos = tbSiaNomProductos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ClaseUtils.isNullObject(bolActivo)) ? 0 : bolActivo.hashCode());
		result = prime * result + ((StringUtils.validateNull(desCatEmpresa)) ? 0 : desCatEmpresa.hashCode());
		result = prime * result + ((ClaseUtils.isNullObject(did)) ? 0 : did.hashCode());
		result = prime * result + ((StringUtils.validateNull(nomCatEmpresa)) ? 0 : nomCatEmpresa.hashCode());
		result = prime * result + ((ClaseUtils.isNullObject(tbSiaEmpresas)) ? 0 : tbSiaEmpresas.hashCode());
		result = prime * result + ((ClaseUtils.isNullObject(tbSiaMarcas)) ? 0 : tbSiaMarcas.hashCode());
		result = prime * result + ((ClaseUtils.isNullObject(tbSiaNomProductos)) ? 0 : tbSiaNomProductos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}

	@Override
	public String toString() {
		return "CategoriaDTO [did=" + did + ", bolActivo=" + bolActivo + ", desCatEmpresa=" + desCatEmpresa
				+ ", nomCatEmpresa=" + nomCatEmpresa + ", tbSiaEmpresas=" + tbSiaEmpresas + ", tbSiaMarcas="
				+ tbSiaMarcas + ", tbSiaNomProductos=" + tbSiaNomProductos + "]";
	}
}
