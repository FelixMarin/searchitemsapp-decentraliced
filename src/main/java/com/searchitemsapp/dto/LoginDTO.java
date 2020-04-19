package com.searchitemsapp.dto;

import com.searchitemsapp.model.TbSiaEmpresa;

public class LoginDTO implements IFdto {

	public LoginDTO() {
		super();
		tbSiaEmpresa = new TbSiaEmpresa();
	}
	
	private Integer did;
	private String codPassword;
	private Integer codPostal;
	private String desEmail;
	private String nomUsuario;
	private String numTelefono;
	private TbSiaEmpresa tbSiaEmpresa;
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getCodPassword() {
		return codPassword;
	}
	public void setCodPassword(String codPassword) {
		this.codPassword = codPassword;
	}
	public Integer getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(Integer codPostal) {
		this.codPostal = codPostal;
	}
	public String getDesEmail() {
		return desEmail;
	}
	public void setDesEmail(String desEmail) {
		this.desEmail = desEmail;
	}
	public String getNomUsuario() {
		return nomUsuario;
	}
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
	public String getNumTelefono() {
		return numTelefono;
	}
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	public TbSiaEmpresa getTbSiaEmpresa() {
		return tbSiaEmpresa;
	}
	public void setTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa) {
		this.tbSiaEmpresa = tbSiaEmpresa;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [did=" + did + ", codPassword=" + codPassword + ", codPostal=" + codPostal + ", desEmail="
				+ desEmail + ", nomUsuario=" + nomUsuario + ", numTelefono=" + numTelefono + ", tbSiaEmpresa="
				+ tbSiaEmpresa + "]";
	}
	
	
}
