package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.model.TbSiaParamsFormLogin;

/**
 * Interfaz que se encarga de gestionar todas las 
 * operaciones de persistencia contra la tabla 
 * 'TbSiaParamsFormLogin' de la base de datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public interface IFParamsFormLogin extends Repository<TbSiaParamsFormLogin, Long>{
	List<ParamsLoginDTO> findAll() throws IOException;
	List<ParamsLoginDTO> findByTbSiaUrl(Integer did) throws IOException;
	ParamsLoginDTO findByDid(Integer did) throws IOException;
}
