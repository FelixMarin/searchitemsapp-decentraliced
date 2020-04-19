package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaSelectoresCss;

public interface IFSelectoresCssRepository  extends Repository<TbSiaSelectoresCss, Long> {
	List<SelectoresCssDTO> findAll() throws IOException;
	SelectoresCssDTO findByDid(Integer did) throws IOException;
	List<SelectoresCssDTO> findByTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa)  throws IOException;
}
