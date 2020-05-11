package com.searchitemsapp.parsers;

import java.util.ArrayList;
import java.util.List;

import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.model.TbSiaLogin;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

public class LoginParser implements IFParser<LoginDTO, TbSiaLogin> {
	
	public LoginParser() {
		super();
	}
	
	public LoginDTO toDTO(TbSiaLogin tbSiaLogin) {	
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),LoginParser.class);
		
		LoginDTO loginPDto = new LoginDTO();
		
		loginPDto.setDid(tbSiaLogin.getDid());
		loginPDto.setCodPassword(tbSiaLogin.getCodPassword());
		loginPDto.setCodPostal(tbSiaLogin.getCodPostal());		
		loginPDto.setDesEmail(tbSiaLogin.getDesEmail());
		loginPDto.setNomUsuario(tbSiaLogin.getNomUsuario());
		loginPDto.setNumTelefono(tbSiaLogin.getNumTelefono());
		loginPDto.setTbSiaEmpresa(tbSiaLogin.getTbSiaEmpresa());
		
		return loginPDto;
	}
	
	public TbSiaLogin toTbSia(LoginDTO loginPDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),LoginParser.class);
		
		TbSiaLogin tbSiaLogin = new TbSiaLogin();
		
		tbSiaLogin.setDid(loginPDto.getDid());
		tbSiaLogin.setCodPassword(loginPDto.getCodPassword());
		tbSiaLogin.setCodPostal(loginPDto.getCodPostal());		
		tbSiaLogin.setDesEmail(loginPDto.getDesEmail());
		tbSiaLogin.setNomUsuario(tbSiaLogin.getNomUsuario());
		tbSiaLogin.setNumTelefono(tbSiaLogin.getNumTelefono());
		tbSiaLogin.setTbSiaEmpresa(loginPDto.getTbSiaEmpresa());
		
		return tbSiaLogin;
	}
	
	public List<LoginDTO> toListDTO(List<TbSiaLogin> lsLoginPDto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),LoginParser.class);
		
		List<LoginDTO> listDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE); 
		LoginDTO loginPDto;
		
		for (TbSiaLogin tbSiaLogin : lsLoginPDto) {
			loginPDto = new LoginDTO();
			loginPDto.setDid(tbSiaLogin.getDid());
			loginPDto.setCodPassword(tbSiaLogin.getCodPassword());
			loginPDto.setCodPostal(tbSiaLogin.getCodPostal());		
			loginPDto.setDesEmail(tbSiaLogin.getDesEmail());
			loginPDto.setNomUsuario(tbSiaLogin.getNomUsuario());
			loginPDto.setNumTelefono(tbSiaLogin.getNumTelefono());
			loginPDto.setTbSiaEmpresa(tbSiaLogin.getTbSiaEmpresa());
			listDto.add(loginPDto);
		}
		
		return listDto;
	}
	
	@Override
	public List<LoginDTO> toListODTO(List<Object[]> objeto) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),LoginParser.class);
		
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}
}
