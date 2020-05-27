package com.searchitemsapp.scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.searchitemsapp.impl.IFUrlImpl;


/**
 * Contiene la funcionalidad necesaria para extraer información de
 * sitios web en los que se necesitan credenciales de acceso. 
 * 
 * @author Felix Marin Ramirez
 *
 */
public class ScrapingLoginUnit extends Scraping {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingLoginUnit.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String ACTION_LOGIN = "ActionLogin";
	private static final String LOGIN = "Login";
	private static final String DOS_PUNTOS = ":";
	private static final String AGENT_ALL = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
	private static final String REFFERER_GOOGLE = "http://www.google.com";
	private static final String HTTP_STATUS_CODE = "HTTP Status Code: ";
	private static final String BASIC = "Basic ";
	private static final String AUTHORIZATION = "Authorization";
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private IFImplementacion<ParamsLoginDTO, CategoriaDTO> paramsFormLoginImpl;
	
	@Autowired
	private IFImplementacion<ParamsLoginDTO, EmpresaDTO> pramsHeadersLoginImpl; 
	
	@Autowired
	private IFImplementacion<LoginDTO, EmpresaDTO> loginImpl;
	
	@Autowired
	private IFUrlImpl urlImpl;
	
	@Autowired
	private CategoriaDTO categoriaDto;
	
	@Autowired 
	private PaisDTO paisDto;	
	
	@Autowired 
	private EmpresaDTO empresaDTO;
	
	@Autowired
	ParamsLoginDTO paramsLoginDto;
	
	/*
	 * Constructor
	 */
	public ScrapingLoginUnit() {
		super();
	}

	/**
	 * Método encargado de extraer información de un html después de 
	 * realizar el login en el sitios web. Si la web necesita un 
	 * usuario logeado para ver los datos, este método realiza el 
	 * proceso login y conexión al sitio web.
	 * 
	 * @param didPais
	 * @param didCategoria
	 * @param iIdEmpresa
	 * @param mapaCookies
	 * @return Map<String, String>
	 * @throws IOException
	 */
	public Map<String, String> checkingHtmlLoginDocument(
			String didPais, String didCategoria, int iIdEmpresa,  
			Map<Integer,Map<String,String>> mapaCookies) throws IOException {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		ResultadoDTO auxResDto = null;
		
		/**
		 * Se comprueba si el módulo está activo consultando una
		 * propieadad del fichero 'flow.properties'.
		 */
		boolean isLoginActivo = Boolean
				.parseBoolean(CommonsPorperties.getValue("flow.value.did.login.activo"));
		
		/**
		 * Si el valor de la propiedad es 'false' 
		 * termina el proceso y retorna nulo.
		 */
		if(!isLoginActivo) {
			return null; 
		}
		
		/**
		 * se setean los ids de pais y categoria en cada uno 
		 * de los objetos globales. Estos objetos son los
		 * parametros del metodo que llama a bbdd y que obtiene
		 * una lista de urls. 
		 */
		paisDto.setDid(desformatearEntero(didPais));
		categoriaDto.setDid(desformatearEntero(didCategoria));
		List<UrlDTO> listUrlDto = urlImpl.obtenerUrlsLogin(paisDto, categoriaDto);
		empresaDTO.setDid(iIdEmpresa);
		
		List<ResultadoDTO> listResUrlLogin = new ArrayList<>(10);
		
		/**
		 * se crea una lista de resultados a partir de
		 * la lista de URLs.
		 */
		for (UrlDTO urlDto : listUrlDto) {
			ResultadoDTO resultadoDto = new ResultadoDTO();
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
    	
		/**
		 * Se filtran todos los que tienen el campo 'ActionLogin' y 
		 * se descarta el resto.
		 */
		for (ResultadoDTO resUrlLogin : listResUrlLogin) {
			if(resUrlLogin.getTbSiaEmpresa().getDid()  == empresaDTO.getDid() &&
					ACTION_LOGIN.equalsIgnoreCase(resUrlLogin.getDesUrl())) {
				auxResDto = resUrlLogin;
			}
		}
		
		/**
		 * Se comprueba que al menos haya un objeto resultado 
		 * en la lista. De otro modo, termina el proceso.
		 */
		if(Objects.isNull(auxResDto)) {
			return null;
		}
		
		/**
		 * Se filtran todos los que tienen el campo 'Login' y 
		 * se descarta el resto.
		 */
		for (ResultadoDTO resUrlLogin : listResUrlLogin) {
			if(resUrlLogin.getTbSiaEmpresa().getDid()  == empresaDTO.getDid() &&
					LOGIN.equalsIgnoreCase(resUrlLogin.getDesUrl())) {
				auxResDto.setLoginUrl(resUrlLogin.getNomUrl());
			}
		}
		
		/**
		 * Se establece el id de la url.
		 */
		paramsLoginDto.getTbSiaUrl().setDid(auxResDto.getDidUrl());
		
		/**
		 * Se obtienen de la bbdd los valores necesarios para
		 * realizar el login en el sitio web. Se obtienen los
		 * parámetros incluidos en el formulario y los headers, 
		 * ambos almacenados en bbdd.
		 */
		List<ParamsLoginDTO> listParamLoginForm = paramsFormLoginImpl.findByTbSia(paramsLoginDto, categoriaDto);
		List<ParamsLoginDTO> listParamLoginHeaders = pramsHeadersLoginImpl.findByTbSia(paramsLoginDto, empresaDTO);
		
		
		/**
		 * Si la lista de parametros del formulario es nula,
		 * se termina el proceso retornando nulo.
		 */
		if(Objects.isNull(listParamLoginForm)) {
			return null;
		}
        
		/**
		 * En este punto se inicia la sessión mediante login de usuario
		 * y se obtienen las cookies de la sesion.
		 */
		Map<String, String> mapLoginPageCookies = obtenerCookiesMethodGet(auxResDto.getLoginnUrl(), 
				listParamLoginHeaders, auxResDto.getDidUrl());
		
		/**
		 * Se crea un mapa con el identificador de la empresa
		 * y la lista de cookies como valor.
		 */
		mapaCookies.put(empresaDTO.getDid(), mapLoginPageCookies);
        
		/**
		 * Se crea un mapa para la lista de parametros del login y
		 * se añaden a la misma todos los parámetros correspondientes
		 * a la url actual.
		 */
		Map<String, String> mapParamsFormLogin = new HashMap<>(10);
        
        for (ParamsLoginDTO paramsLoginDTO : listParamLoginForm) {
        	if(auxResDto.getDidUrl() == paramsLoginDTO.getTbSiaUrl().getDid()) {
        		mapParamsFormLogin.put(paramsLoginDTO.getParamClave(), paramsLoginDTO.getParamValor());
        	}
		}
        
        /**
         * se obtienen el usuario y la contraseña con los que
         * se va a realizar el login en formato base 64.
         */
        String b64login = setB64encode(null, empresaDTO);
        
        /**
         * En este punto se establece el login y 
         * se inicia la sesion en el sitio web.
         */
        logearseEnSitioWeb(auxResDto.getNomUrl(), mapParamsFormLogin, mapLoginPageCookies, b64login);
        
        return mapLoginPageCookies;
    }
	
	/**
	 * Convierte a codificación base 64 tanto el login como el password 
	 * del usuario virtual con el que la aplicación realizará login en 
	 * el sitio web del que se extraerá la información.
	 * 
	 * @param loginDTO
	 * @param empresaDTO
	 * @return String
	 * @throws IOException
	 */
	private String setB64encode(final LoginDTO loginDTO, final EmpresaDTO empresaDTO) throws IOException {
		
		/**
		 * Se consulta en bbdd los datos de usuario virtual 
		 * con el que se va a logear la aplicaión al sitio web.
		 */
		List<LoginDTO> listloginDto = loginImpl.findByTbSia(loginDTO, empresaDTO);
		String b64login = "null";
        
		/**
		 * Se concatenan tanto el usuario como la contraseña 
		 * a una cadena de caracteres y se transforma a base 64.
		 */
        if(Objects.nonNull(listloginDto)) {
	        String login = listloginDto.get(0).getNomUsuario()
	        		.concat(DOS_PUNTOS)
	        		.concat(listloginDto.get(0).getCodPassword());
	        
	        b64login = Base64.getEncoder().encodeToString(login.getBytes());
        }
        
        /**
         * retorna la cadena en formato base 64.
         */
        return b64login;
	}
	
	/**
	 * Este método devuelve un objeto de tipo Map con 
	 * las cookies de la sesion del usuario tras el 
	 * login.
	 * 
	 * @param url
	 * @param listParamLoginHeaders
	 * @param idUrl
	 * @return Map<String, String>
	 */
	@SuppressWarnings("deprecation")
	private Map<String, String> obtenerCookiesMethodGet(final String url, 
			final List<ParamsLoginDTO> listParamLoginHeaders, 
			final int idUrl) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		Connection connection;
		Response response  = null;
		
		if(Objects.isNull(listParamLoginHeaders)) {
			return new HashMap<>();
		}
		
		try {
			
			connection = Jsoup.connect(url)
					.referrer(url)
					.userAgent(AGENT_ALL)
					.method(Connection.Method.GET)
					.referrer(REFFERER_GOOGLE)
					.ignoreContentType(Boolean.TRUE)
					.validateTLSCertificates(Boolean.FALSE)
					.ignoreHttpErrors(Boolean.TRUE)
					.timeout(100000);
			
			for (ParamsLoginDTO paramsLoginDTO : listParamLoginHeaders) {
				if(idUrl == paramsLoginDTO.getTbSiaUrl().getDid()) {
					connection.header(paramsLoginDTO.getParamClave(), paramsLoginDTO.getParamValor());
				}
			}
			
			response = connection.execute();	
			
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(HTTP_STATUS_CODE.concat(String.valueOf(response.statusCode())));
			}
		} catch (IOException e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
			}
		}
		
		return response.cookies();
	}
	
	/**
	 * Método que establece el login en el sitio 
	 * web usando los parametros del usuario virtual. 
	 * 
	 * @param url
	 * @param mapParams
	 * @param mapLoginPageCookies
	 * @param b64login
	 */
	private void logearseEnSitioWeb(final String url, 
			final Map<String, String> mapParams, 
			final Map<String, String> mapLoginPageCookies, 
			final String b64login) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		Connection connection  = null;
		Response response  = null;
		
		/**
		 * Configuración del objeto que realizará 
		 * el login en el sitio web.
		 */
		try {
			connection = Jsoup.connect(url)
					.referrer(url)
					.method(Connection.Method.POST)
					.userAgent(AGENT_ALL)
					.ignoreContentType(Boolean.TRUE)
		            .data(mapParams)
		            .cookies(mapLoginPageCookies)
		            .followRedirects(Boolean.TRUE)
					.timeout(100000); 
			
			if(Objects.nonNull(b64login)) {
				connection.header(AUTHORIZATION,BASIC.concat(b64login));
			}

			/**
			 * Se establece la conexión con el sitio web.
			 */
			response = connection.execute();
			
			/**
			 * Se pinta el estado de la conexion en las trazas de log.
			 */
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(HTTP_STATUS_CODE.concat(String.valueOf(response.statusCode())));
			}
		} catch (IOException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		}
	}	
	
	/**
	 * Convierte una cadena a tipo int
	 *
	 * @param pStrCadena
	 * @return
	 */
	private int desformatearEntero(String pStrCadena) {

		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		int iResultado = 0;
		if (!"".contentEquals(pStrCadena)) {
			try {
				iResultado = Integer.parseInt(pStrCadena);
			} catch (NumberFormatException nfe) {
				if(LOGGER.isErrorEnabled()) {
					LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),nfe);
				}
			}
		}
		return iResultado;
	}
}
