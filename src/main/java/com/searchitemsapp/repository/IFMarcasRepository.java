package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.model.TbSiaMarcas;

public interface IFMarcasRepository  extends Repository<TbSiaMarcas, Long>{
	List<MarcasDTO> findAll() throws IOException;
	MarcasDTO findByDid(Integer did) throws IOException;
}
