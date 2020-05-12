package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.model.TbSiaCategoriasEmpresa;
 
/**
 * Interfaz de marcador de repositorio. 
 * Captura el tipo de dominio para administrar, 
 * así como el tipo de identificación del tipo de 
 * dominio. El propósito general es mantener la 
 * información de tipo y poder descubrir interfaces 
 * que extiendan esta durante el escaneo de classpath 
 * para crear fácilmente Spring bean.
 * 
 * @author Felix Marin Ramirez
 *
 */
public interface IFCategoriaRepository extends Repository<TbSiaCategoriasEmpresa, Long> {
	List<CategoriaDTO> findAll() throws IOException;
	CategoriaDTO findByDid(Integer did);
	List<CategoriaDTO> findByBolActivo(@Param("activo") Boolean activo) throws IOException;
}
