package com.searchitemsapp.scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.LoginDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.dto.ParamsLoginDTO;
import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.IFImplementacion;
import com.searchitemsapp.impl.UrlImpl;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings({"unchecked","rawtypes","deprecation"})
public class ScrapingLoginUnit extends Scraping {
	

	public ScrapingLoginUnit() {
		super();
	}
		
	@Autowired
	private IFImplementacion<ParamsLoginDTO, CategoriaDTO> paramsFormLoginImpl;
	
	@Autowired
	private IFImplementacion<ParamsLoginDTO, EmpresaDTO> pramsHeadersLoginImpl; 
	
	@Autowired
	private IFImplementacion<LoginDTO, EmpresaDTO> loginImpl;
	
	@Autowired
	private UrlImpl urlImpl;
	
	@Autowired
	private CategoriaDTO categoriaDto;
	
	@Autowired 
	private PaisDTO paisDto;	
	
	@Autowired 
	private EmpresaDTO empresaDTO;
	
	@Autowired
	ParamsLoginDTO paramsLoginDto;

	public Map<String, String> checkingHtmlLoginDocument(
			String didPais, String didCategoria, int iIdEmpresa,  
			Map<Integer,Map<String,String>> mapaCookies) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		ResultadoDTO auxResDto = (ResultadoDTO) ClaseUtils.NULL_OBJECT;
		String b64login;
		List<ResultadoDTO> listResUrlLogin;
		ResultadoDTO resultadoDto;
		List<UrlDTO> listUrlDto;
		Map<String, String> mapLoginPageCookies;
		 Map<String, String> mapParamsFormLogin;		
		List<ParamsLoginDTO> listParamLoginForm;
		List<ParamsLoginDTO> listParamLoginHeaders;
		
		boolean isLoginActivo = Boolean
				.parseBoolean(CommonsPorperties.getValue("flow.value.did.login.activo"));
		
		if(!isLoginActivo) {
			return (HashMap) ClaseUtils.NULL_OBJECT; 
		}
		
		paisDto.setDid(StringUtils.desformatearEntero(didPais));
		categoriaDto.setDid(StringUtils.desformatearEntero(didCategoria));
		listUrlDto = urlImpl.obtenerUrlsLogin(paisDto, categoriaDto);
		empresaDTO.setDid(iIdEmpresa);
		
		listResUrlLogin = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		
		for (UrlDTO urlDto : listUrlDto) {
			resultadoDto = new ResultadoDTO();
			resultadoDto.setNomUrl(urlDto.getNomUrl());
			resultadoDto.setDidEmpresa(urlDto.getTbSiaEmpresa().getDid());
			resultadoDto.setDidUrl(urlDto.getDid());
			resultadoDto.setBolActivo(urlDto.getBolActivo());
			resultadoDto.getTbSiaEmpresa().setNomEmpresa(urlDto.getTbSiaEmpresa().getNomEmpresa());
			resultadoDto.setBolStatus(urlDto.getBolStatus());
			resultadoDto.setBolLogin(urlDto.getBolLogin());
			resultadoDto.setDesUrl(urlDto.getDesUrl());
			listResUrlLogin.add(resultadoDto);
		}
    	
		for (ResultadoDTO resUrlLogin : listResUrlLogin) {
			if(resUrlLogin.getTbSiaEmpresa().getDid()  == empresaDTO.getDid() &&
					StringUtils.ACTION_LOGIN.equalsIgnoreCase(resUrlLogin.getDesUrl())) {
				auxResDto = resUrlLogin;
			}
		}
		
		if(ClaseUtils.isNullObject(auxResDto)) {
			return (HashMap) ClaseUtils.NULL_OBJECT;
		}
		
		for (ResultadoDTO resUrlLogin : listResUrlLogin) {
			if(resUrlLogin.getTbSiaEmpresa().getDid()  == empresaDTO.getDid() &&
					StringUtils.LOGIN.equalsIgnoreCase(resUrlLogin.getDesUrl())) {
				auxResDto.setLoginUrl(resUrlLogin.getNomUrl());
			}
		}
		
		paramsLoginDto.getTbSiaUrl().setDid(auxResDto.getDidUrl());
		
		listParamLoginForm = paramsFormLoginImpl.findByTbSia(paramsLoginDto, categoriaDto);
		listParamLoginHeaders = pramsHeadersLoginImpl.findByTbSia(paramsLoginDto, empresaDTO);
		
		
		if(ClaseUtils.isNullObject(listParamLoginForm)) {
			return (HashMap) ClaseUtils.NULL_OBJECT;
		}
            
		mapLoginPageCookies = obtenerCookiesMethodGet(auxResDto.getLoginnUrl(), 
				listParamLoginHeaders, auxResDto.getDidUrl());
		
		mapaCookies.put(empresaDTO.getDid(), mapLoginPageCookies);
        
        mapParamsFormLogin = new HashMap<>(ClaseUtils.DEFAULT_INT_VALUE);
        
        for (ParamsLoginDTO paramsLoginDTO : listParamLoginForm) {
        	if(auxResDto.getDidUrl() == paramsLoginDTO.getTbSiaUrl().getDid()) {
        		mapParamsFormLogin.put(paramsLoginDTO.getParamClave(), paramsLoginDTO.getParamValor());
        	}
		}
        
        b64login = setB64encode((LoginDTO) ClaseUtils.NULL_OBJECT, empresaDTO);
        
        logearseEnSitioWeb(auxResDto.getNomUrl(), mapParamsFormLogin, mapLoginPageCookies, b64login);
        
        return mapLoginPageCookies;
    }
	
	private String setB64encode(final LoginDTO loginDTO, final EmpresaDTO empresaDTO) throws IOException {
		
		List<LoginDTO> listloginDto = loginImpl.findByTbSia(loginDTO, empresaDTO);
		String b64login = StringUtils.NULL_STRING;
        
        if(!ClaseUtils.isNullObject(listloginDto)) {
	        String login = listloginDto.get(ClaseUtils.ZERO_INT).getNomUsuario()
	        		.concat(StringUtils.DOS_PUNTOS)
	        		.concat(listloginDto.get(ClaseUtils.ZERO_INT).getCodPassword());
	        
	        b64login = Base64.getEncoder().encodeToString(login.getBytes());
        }
        
        return b64login;
	}
	
	/**
	 * Con este metodo devuelvo un objeto de tipo Mapa con las cookies necesarias
	 * para realizar la conexion a la web
	 * 
	 * @param url
	 * @param listParamLoginHeaders
	 * @param idUrl
	 * @return Mapa con el listado de cookies
	 */
	private Map<String, String> obtenerCookiesMethodGet(final String url, 
			final List<ParamsLoginDTO> listParamLoginHeaders, 
			final int idUrl) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		Connection connection;
		Response response  = (Response) ClaseUtils.NULL_OBJECT;
		
		if(ClaseUtils.isNullObject(listParamLoginHeaders)) {
			return new HashMap<>();
		}
		
		try {
			
			connection = Jsoup.connect(url)
					.referrer(url)
					.userAgent(StringUtils.AGENT_ALL)
					.method(Connection.Method.GET)
					.referrer(StringUtils.REFFERER_GOOGLE)
					.ignoreContentType(Boolean.TRUE)
					.validateTLSCertificates(Boolean.FALSE)
					.ignoreHttpErrors(Boolean.TRUE)
					.timeout(ClaseUtils.TIME_OUT);
			
			for (ParamsLoginDTO paramsLoginDTO : listParamLoginHeaders) {
				if(idUrl == paramsLoginDTO.getTbSiaUrl().getDid()) {
					connection.header(paramsLoginDTO.getParamClave(), paramsLoginDTO.getParamValor());
				}
			}
			
			response = connection.execute();	
			
			LogsUtils.escribeLogDebug(StringUtils.HTTP_STATUS_CODE.concat(String.valueOf(response.statusCode())),Scraping.class);
			
		} catch (IOException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
		
		return response.cookies();
	}
	
	private void logearseEnSitioWeb(final String url, 
			final Map<String, String> mapParams, 
			final Map<String, String> mapLoginPageCookies, 
			final String b64login) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		Connection connection  = (Connection) ClaseUtils.NULL_OBJECT;
		Response response  = (Response) ClaseUtils.NULL_OBJECT;
		
		try {
			connection = Jsoup.connect(url)
					.referrer(url)
					.method(Connection.Method.POST)
					.userAgent(StringUtils.AGENT_ALL)
					.ignoreContentType(Boolean.TRUE)
		            .data(mapParams)
		            .cookies(mapLoginPageCookies)
		            .followRedirects(Boolean.TRUE)
					.timeout(ClaseUtils.TIME_OUT); 
			
			if(!StringUtils.validateNull(b64login)) {
				connection.header(StringUtils.AUTHORIZATION,StringUtils.BASIC.concat(b64login));
			}
					
			response = connection.execute();
			
			LogsUtils.escribeLogDebug(StringUtils.HTTP_STATUS_CODE.concat(String.valueOf(response.statusCode())),this.getClass());
			
		} catch (IOException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
		}
	}	
}
