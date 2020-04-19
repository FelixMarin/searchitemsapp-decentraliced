package com.searchitemsapp.dto;

import java.util.Objects;

import com.searchitemsapp.util.StringUtils;

public class PedidoDTO implements IFdto {
	
	private String categoria;
	private String producto;

	public PedidoDTO() {
		super();
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((StringUtils.validateNull(categoria)) ? 0 : categoria.hashCode());
		result = prime * result + ((StringUtils.validateNull(producto)) ? 0 : producto.hashCode());
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
