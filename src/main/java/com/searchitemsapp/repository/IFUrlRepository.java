package com.searchitemsapp.repository;
import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.model.TbSiaUrl;

/**
 * Interfaz que se encarga de gestionar todas las 
 * operaciones de persistencia contra la tabla 
 * 'TbSiaUrl' de la base de datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public interface  IFUrlRepository  extends Repository<TbSiaUrl, Long>{
	
	List<UrlDTO> findAll() throws IOException;
	UrlDTO findByDid(Integer did)  throws IOException;
	List<UrlDTO> findByDidAndDesUrl(Integer didPais, String didCategoria) throws IOException;
	List<UrlDTO> findByDidAndNomUrl(Integer didPais, String didCategoria) throws IOException;
}
