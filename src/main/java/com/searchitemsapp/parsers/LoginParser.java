package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.entities.TbSiaEmpresa;
import com.searchitemsapp.entities.TbSiaLogin;



/**
 * Es un componente analizador de software que 
 * toma datos de entrada y construye una 
 * estructura de datos. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class LoginParser implements IFParser<LoginDTO, TbSiaLogin> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginParser.class);  
	
	/*
	 * Constructor
	 */
	public LoginParser() {
		super();
	}
	
	/**
	 * Mapea los datos de un objeto de tipo Entity a un objeto de tipo DTO.
	 * 
	 * @param TbSiaLogin
	 * @return LoginDTO
	 */
	@Override
	public LoginDTO toDTO(TbSiaLogin tbSiaLogin) {	
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		LoginDTO loginPDto = new LoginDTO();
		
		loginPDto.setDid(tbSiaLogin.getDid());
		loginPDto.setCodPassword(tbSiaLogin.getCodPassword());
		loginPDto.setCodPostal(tbSiaLogin.getCodPostal());		
		loginPDto.setDesEmail(tbSiaLogin.getDesEmail());
		loginPDto.setNomUsuario(tbSiaLogin.getNomUsuario());
		loginPDto.setNumTelefono(tbSiaLogin.getNumTelefono());
		loginPDto.setDidEmpresa(tbSiaLogin.getTbSiaEmpresa().getDid());
		loginPDto.setNomEmpresa(tbSiaLogin.getTbSiaEmpresa().getNomEmpresa());
		
		return loginPDto;
	}
	
	/**
	 * Mapea los datos de un objeto de tipo DTO a un objeto de tipo Entity.
	 * 
	 * @param LoginDTO
	 * @return TbSiaLogin
	 */
	@Override
	public TbSiaLogin toTbSia(LoginDTO loginPDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		TbSiaLogin tbSiaLogin = new TbSiaLogin();
		
		tbSiaLogin.setDid(loginPDto.getDid());
		tbSiaLogin.setCodPassword(loginPDto.getCodPassword());
		tbSiaLogin.setCodPostal(loginPDto.getCodPostal());		
		tbSiaLogin.setDesEmail(loginPDto.getDesEmail());
		tbSiaLogin.setNomUsuario(loginPDto.getNomUsuario());
		tbSiaLogin.setNumTelefono(loginPDto.getNumTelefono());
		tbSiaLogin.setTbSiaEmpresa(new TbSiaEmpresa());
		tbSiaLogin.getTbSiaEmpresa().setDid(loginPDto.getDid());
		tbSiaLogin.getTbSiaEmpresa().setNomEmpresa(loginPDto.getNomEmpresa());
		
		return tbSiaLogin;
	}
	
	/**
	 * Mapea una lista de de Entities a una lista de DTOs.
	 * 
	 * @param List<TbSiaLogin>
	 * @return List<LoginDTO>
	 */
	@Override
	public List<LoginDTO> toListDTO(List<TbSiaLogin> lsLoginPDto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		List<LoginDTO> listDto = new ArrayList<>(NumberUtils.INTEGER_ONE); 
		LoginDTO loginPDto;
		
		for (TbSiaLogin tbSiaLogin : lsLoginPDto) {
			loginPDto = new LoginDTO();
			loginPDto.setDid(tbSiaLogin.getDid());
			loginPDto.setCodPassword(tbSiaLogin.getCodPassword());
			loginPDto.setCodPostal(tbSiaLogin.getCodPostal());		
			loginPDto.setDesEmail(tbSiaLogin.getDesEmail());
			loginPDto.setNomUsuario(tbSiaLogin.getNomUsuario());
			loginPDto.setNumTelefono(tbSiaLogin.getNumTelefono());
			loginPDto.setDidEmpresa(tbSiaLogin.getTbSiaEmpresa().getDid());
			loginPDto.setNomEmpresa(tbSiaLogin.getTbSiaEmpresa().getNomEmpresa());
			listDto.add(loginPDto);
		}
		
		return listDto;
	}
	
	/**
	 * Método no implementado.
	 */
	@Override
	public List<LoginDTO> toListODTO(List<Object[]> objeto) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		return new ArrayList<>(NumberUtils.INTEGER_ONE);
	}
}
