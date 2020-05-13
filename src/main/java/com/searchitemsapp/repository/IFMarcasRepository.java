package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.MarcasDTO;
import com.searchitemsapp.model.TbSiaMarcas;

/**
 * Interfaz que se encarga de gestionar todas las 
 * operaciones de persistencia contra la tabla 
 * 'TbSiaMarcas' de la base de datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public interface IFMarcasRepository  extends Repository<TbSiaMarcas, Long>{
	List<MarcasDTO> findAll() throws IOException;
	MarcasDTO findByDid(Integer did) throws IOException;
}
