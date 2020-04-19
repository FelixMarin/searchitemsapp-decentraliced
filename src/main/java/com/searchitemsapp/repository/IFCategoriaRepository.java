package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;

public interface IFCategoriaRepository extends Repository<TbSiaCategoriasEmpresa, Long> {
	List<CategoriaDTO> findAll() throws IOException;
	CategoriaDTO findByDid(Integer did);
	List<CategoriaDTO> findByBolActivo(@Param("activo") Boolean activo) throws IOException;
}
