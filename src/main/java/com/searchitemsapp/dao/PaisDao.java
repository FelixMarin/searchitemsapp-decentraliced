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

@Repository
public class PaisDao extends AbstractDao<PaisDTO, TbSiaPais> implements IFPaisRepository {

	private static final String PAIS_PARSER = "PAIS_PARSER";

	public PaisDao() {
		super();
	}
		
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<LoginDTO>
	 */
	@Override
	public PaisDTO findByDid(Integer did) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(ClaseUtils.isNullObject(did)) {
			return (PaisDTO) ClaseUtils.NULL_OBJECT;
		}
		
		PaisDTO paisDto = (PaisDTO) ClaseUtils.NULL_OBJECT;
		
		final StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.empresa.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(did);		
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
		
		try {
			paisDto = getParser(PAIS_PARSER).toDTO(getEntityManager().find(TbSiaPais.class, did));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return paisDto;
	}
}
