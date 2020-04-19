package com.searchitemsapp.dto;

import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaPais;
import com.searchitemsapp.util.ClaseUtils;

public class MarcasDTO implements IFdto {

	public MarcasDTO() {
		super();
	}

	private Integer did;
	private String nomMarca;
	private TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa;
	private TbSiaPais tbSiaPais;

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getNomMarca() {
		return nomMarca;
	}

	public void setNomMarca(String nomMarca) {
		this.nomMarca = nomMarca;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((did == null) ? 0 : did.hashCode());
		result = prime * result + ((nomMarca == null) ? 0 : nomMarca.hashCode());
		result = prime * result + ((ClaseUtils.isNullObject(tbSiaCategoriasEmpresa)) ? 0 : tbSiaCategoriasEmpresa.hashCode());
		result = prime * result + ((ClaseUtils.isNullObject(tbSiaPais)) ? 0 : tbSiaPais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarcasDTO other = (MarcasDTO) obj;
		if (did == ClaseUtils.NULL_OBJECT) {
			if (other.did != null)
				return false;
		} else if (!did.equals(other.did)) {
			return false;
		}
		if (nomMarca == null) {
			if (other.nomMarca != null)
				return false;
		} else if (!nomMarca.equals(other.nomMarca)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MarcasDTO [did=" + did + ", nomMarca=" + nomMarca + ", tbSiaCategoriasEmpresa=" + tbSiaCategoriasEmpresa
				+ ", tbSiaPais=" + tbSiaPais + "]";
	}
}
