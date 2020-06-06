package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.Objects;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.entities.TbSiaPais;
import com.searchitemsapp.repository.IFPaisRepository;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(PaisDao.class);     
	
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
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Si el parametro de entrada es nulo, el proceso
		 * termina y retorna nulo.
		 */
		if(Objects.isNull(did)) {
			return null;
		}
		
		PaisDTO paisDto = null;
		
		/**
		 * Se compone el mensaje que se mostrará como unta traza
		 * en el fichero de logs. Pinta el identificador de la marca.
		 */
		final StringBuilder debugMessage = new StringBuilder(NumberUtils.INTEGER_ONE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE);
		debugMessage.append(did);	
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(debugMessage.toString(),this.getClass());
		}
		
		/**
		 * Se obtiene el resutlado y se mapea a un objeto de tipo DTO.
		 * Si no hay resultado la excepcion se traza en los logs.
		 */
		try {
			paisDto = getParser(PAIS_PARSER).toDTO(getEntityManager().find(TbSiaPais.class, did));
		}catch(NoResultException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
		
		return paisDto;
	}
}
