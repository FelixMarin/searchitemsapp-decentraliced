package com.searchitemsapp.dto;

import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaPais;

public class NomProductoDTO  implements IFdto {

	public NomProductoDTO() {
		super();
	}

	private Integer did;
	private String nomProducto;
	private TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa;
	private TbSiaPais tbSiaPais;
	
	public Integer getDid() {
		return did;
	}
	
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getNomProducto() {
		return nomProducto;
	}
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
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
	
	@Override
	public String toString() {
		return "NomProductoDTO [did=" + did + ", nomProducto=" + nomProducto + ", tbSiaCategoriasEmpresa="
				+ tbSiaCategoriasEmpresa + ", tbSiaPais=" + tbSiaPais + "]";
	}
}
