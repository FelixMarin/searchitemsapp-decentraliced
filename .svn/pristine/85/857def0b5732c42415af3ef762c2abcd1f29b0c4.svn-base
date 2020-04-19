package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaLogin;

public interface IFLoginRepository extends Repository<TbSiaLogin, Long> {
	List<LoginDTO> findAll() throws IOException;
	LoginDTO findByDid(Integer did);
	LoginDTO findByTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa)  throws IOException;
}
