package com.searchitemsapp.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.model.TbSiaNomProducto;

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
public interface IFNomProductoRepository  extends Repository<TbSiaNomProducto, Long> {
	List<NomProductoDTO> findAll() throws IOException;
	NomProductoDTO findByDid(Integer did) throws IOException;
}
