package com.searchitemsapp.repository;
import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.model.TbSiaUrl;

public interface  IFUrlRepository  extends Repository<TbSiaUrl, Long>{
	UrlDTO findByDid(Integer did)  throws IOException;
	List<UrlDTO> findByTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa)  throws IOException;
	List<UrlDTO> obtenerUrls(Integer didPais, Integer didCategoria) throws IOException;
	List<UrlDTO> obtenerUrlsLogin(Integer didPais, Integer didCategoria) throws IOException;
	List<UrlDTO>  obtenerUrlsEmpresa(Integer didPais, Integer didCategoria, List<Integer> listDidEmpresas) throws IOException;
}
