package com.searchitemsapp.dto;

import java.util.Objects;

import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
import com.searchitemsapp.model.TbSiaPais;

/**
 * Objeto de Transferencia de Datos (DTO) 
 * es un objeto que transporta datos entre procesos.
 * No tiene más comportamiento que almacenar y entregar 
 * sus propios datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class MarcasDTO implements IFdto {

	/*
	 * Variables Globales
	 */
	private Integer did;
	private String nomMarca;
	private TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa;
	private TbSiaPais tbSiaPais;
	
	/*
	 * Constructor
	 */
	public MarcasDTO() {
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

	/*
	 * Métodos sobre-escritos
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Objects.isNull(did)) ? 0 : did.hashCode());
		result = prime * result + ((Objects.isNull(nomMarca)) ? 0 : nomMarca.hashCode());
		result = prime * result + ((Objects.isNull(tbSiaCategoriasEmpresa)) ? 0 : tbSiaCategoriasEmpresa.hashCode());
		result = prime * result + ((Objects.isNull(tbSiaPais)) ? 0 : tbSiaPais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (Objects.isNull(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarcasDTO other = (MarcasDTO) obj;
		if (Objects.isNull(did)) {
			if (Objects.nonNull(other.did))
				return false;
		} else if (!did.equals(other.did)) {
			return false;
		}
		if (Objects.isNull(nomMarca)) {
			if (Objects.nonNull(other.nomMarca))
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
