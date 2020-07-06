package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.model.TbSiaLogin;

/**
 * Interfaz que se encarga de gestionar todas las 
 * operaciones de persistencia contra la tabla 
 * 'TbSiaLogin' de la base de datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public interface IFLoginRepository extends Repository<TbSiaLogin, Long> {
	List<LoginDTO> findAll() throws IOException;
	LoginDTO findByDid(Integer did);
	LoginDTO findByTbSiaEmpresa(Integer did)  throws IOException;
}
