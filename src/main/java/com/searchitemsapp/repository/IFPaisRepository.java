package com.searchitemsapp.repository;

import java.io.IOException;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.entities.TbSiaPais;

/**
 * Interfaz que se encarga de gestionar todas las 
 * operaciones de persistencia contra la tabla 
 * 'TbSiaPais' de la base de datos.
 * 
 * @author Felix Marin Ramirez
 *
 */
public interface IFPaisRepository  extends Repository<TbSiaPais, Long> {
	PaisDTO findByDid(Integer did)  throws IOException;
}
