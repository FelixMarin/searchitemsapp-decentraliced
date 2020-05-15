package com.searchitemsapp.dao;

import java.io.IOException;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.model.TbSiaPais;
import com.searchitemsapp.repository.IFPaisRepository;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * Encapsula el acceso a la base de datos. Por lo que cuando la capa 
 * de lógica de negocio necesite interactuar con la base de datos, va 
 * a hacerlo a través de la API que le ofrece el DAO.
 * 
 * @author Felix Marin Ramirez
 *
 */
@Repository
public class PaisDao extends AbstractDao<PaisDTO, TbSiaPais> implements IFPaisRepository {

	/*
	 * Constantes Globales
	 */
	private static final String PAIS_PARSER = "PAIS_PARSER";

	/*
	 * Constructor 
	 */
	public PaisDao() {
		super();
	}
		
	/**
	 * A partir de un indentifcador se obtiene un elemento
	 * de la tabla.
	 * 
	 * @return PaisDTO
	 */
	@Override
	public PaisDTO findByDid(Integer did) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(ClaseUtils.isNullObject(did)) {
			return (PaisDTO) ClaseUtils.NULL_OBJECT;
		}
		
		PaisDTO paisDto = (PaisDTO) ClaseUtils.NULL_OBJECT;
		
		/**
		 * Se compone el mensaje que se mostrará como unta traza
		 * en el fichero de logs. Pinta el identificador de la marca.
		 */
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(did);		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			paisDto = getParser(PAIS_PARSER).toDTO(getEntityManager().find(TbSiaPais.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return paisDto;
	}
}
