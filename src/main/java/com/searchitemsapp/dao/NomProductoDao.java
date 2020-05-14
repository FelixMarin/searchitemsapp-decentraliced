package com.searchitemsapp.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.NomProductoDTO;
import com.searchitemsapp.model.TbSiaNomProducto;
import com.searchitemsapp.repository.IFNomProductoRepository;
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
@SuppressWarnings("unchecked")
@Repository
public class NomProductoDao extends AbstractDao<NomProductoDTO, TbSiaNomProducto> implements IFNomProductoRepository {
	
	private static final String NOM_PRODUCTO_PARSER = "NOM_PRODUCTO_PARSER";

	public NomProductoDao() {
		super();
	}
	
	/**
	 * Método que devuelve todos los elementos de una tabla.
	 * 
	 * @return List<LoginDTO>
	 */
	@Override
	public List<NomProductoDTO> findAll() throws IOException {
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<NomProductoDTO> resultado = (List<NomProductoDTO>) ClaseUtils.NULL_OBJECT;
		
		StringBuilder queryBuilder = StringUtils.getNewStringBuilder();
		queryBuilder.append(CommonsPorperties.getValue("flow.value.nomproducto.select.all"));
		
		isEntityManagerOpen(this.getClass());
		
		Query q = getEntityManager().createQuery(queryBuilder.toString(), TbSiaNomProducto.class);
		
		try {
			resultado = getParser(NOM_PRODUCTO_PARSER).toListDTO(((List<TbSiaNomProducto>) q.getResultList()));
		}catch(NoResultException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return resultado;
	}
	
	/**
	 * A partir de un indentifcador se obtiene un elemento
	 * de la tabla.
	 * 
	 * @return NomProductoDTO
	 */
	@Override
	public NomProductoDTO findByDid(Integer did) throws IOException {
		return getParser(NOM_PRODUCTO_PARSER).toDTO(getEntityManager().find(TbSiaNomProducto.class, did));
	}
}
