package com.searchitemsapp.dto;

import java.util.Objects;



/**
 * Objeto de Transferencia de Datos (DTO) 
 * es un objeto que transporta datos entre procesos.
 * No tiene más comportamiento que almacenar y entregar 
 * sus propios datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public class PedidoDTO implements IFdto {
	
	/*
	 * Variables Globales
	 */
	private String categoria;
	private String producto;

	/*
	 * Constructor
	 */
	public PedidoDTO() {
		super();
	}

	/*
	 * Métodos Getters y Setters
	 */
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	/*
	 * Métodos sobre-escitos
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Objects.isNull(categoria)) ? 0 : categoria.hashCode());
		result = prime * result + ((Objects.isNull(producto)) ? 0 : producto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}

	@Override
	public String toString() {
		return "PedidoDTO [categoria=" + categoria + ", producto=" + producto + "]";
	}
	
}
