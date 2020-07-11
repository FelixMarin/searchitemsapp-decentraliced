package com.searchitemsapp.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDTO implements IFdto {

	/*
	 * Variables Globales
	 */
	private Integer did;
	private String codPassword;
	private Integer codPostal;
	private String desEmail;
	private String nomUsuario;
	private String numTelefono;	
	private Integer didEmpresa;
	private String nomEmpresa;

	/*
	 * Constructor
	 */
	public LoginDTO() {
		super();
	}
	
	/*
	 * Metodos Getters y Setters
	 */
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
	
	public Integer getDidEmpresa() {
		return didEmpresa;
	}

	public void setDidEmpresa(Integer didEmpresa) {
		this.didEmpresa = didEmpresa;
	}

	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}

	/*
	 * Métodos sobre-escritos. 
	 */
	@Override
	public String toString() {
		return "LoginDTO [did=" + did + ", codPassword=" + codPassword + ", codPostal=" + codPostal + ", desEmail="
				+ desEmail + ", nomUsuario=" + nomUsuario + ", numTelefono=" + numTelefono + "]";
	}
	
	
}
