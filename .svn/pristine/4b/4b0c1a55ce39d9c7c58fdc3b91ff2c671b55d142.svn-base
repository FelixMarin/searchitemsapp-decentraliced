package com.searchitemsapp.repository;

import java.io.IOException;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.model.TbSiaPais;

public interface IFPaisRepository  extends Repository<TbSiaPais, Long> {
	PaisDTO findByDid(Integer did)  throws IOException;
}
