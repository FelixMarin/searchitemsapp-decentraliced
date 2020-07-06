package com.searchitemsapp.dto;

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
public class NomProductoDTO  implements IFdto {

	/*
	 * Variables Globales
	 */
	private Integer did;
	private String nomProducto;
	private TbSiaCategoriasEmpresa tbSiaCategoriasEmpresa;
	private TbSiaPais tbSiaPais;
	
	/*
	 * Contructor
	 */
	public NomProductoDTO() {
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
	
	/*
	 * Métodos sobre-escritos
	 */
	@Override
	public String toString() {
		return "NomProductoDTO [did=" + did + ", nomProducto=" + nomProducto + ", tbSiaCategoriasEmpresa="
				+ tbSiaCategoriasEmpresa + ", tbSiaPais=" + tbSiaPais + "]";
	}
}
