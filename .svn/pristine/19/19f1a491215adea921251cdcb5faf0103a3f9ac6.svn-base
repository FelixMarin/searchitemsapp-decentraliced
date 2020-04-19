package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.model.TbSiaParamsFormLogin;
import com.searchitemsapp.model.TbSiaUrl;

public interface IFParamsFormLogin extends Repository<TbSiaParamsFormLogin, Long>{
	List<ParamsLoginDTO> findAll() throws IOException;
	List<ParamsLoginDTO> findByTbSiaUrl(TbSiaUrl tbSiaUrl) throws IOException;
	ParamsLoginDTO findByDid(Integer did) throws IOException;
}
