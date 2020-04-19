package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.model.TbSiaNomProducto;

public interface IFNomProductoRepository  extends Repository<TbSiaNomProducto, Long> {
	List<NomProductoDTO> findAll() throws IOException;
	NomProductoDTO findByDid(Integer did) throws IOException;
}
