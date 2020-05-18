package com.searchitemsapp.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.TypeDef;

/**
 * Definición de tipos customizados.
 */
@TypeDef(
		   name = "didDef",
		   defaultForType = Integer.class,
		   typeClass = Integer.class
		)

@TypeDef(
		   name = "bolAct",
		   defaultForType = Boolean.class,
		   typeClass = Boolean.class
		)

@TypeDef(
		   name = "strDef",
		   defaultForType = String.class,
		   typeClass = String.class
		)

/**
 * The persistent class for the tb_sia_login database table.
 * @author Felix Marin Ramirez
 *
 */
@Entity
@Table(name="tb_sia_login", schema = "sia")
@NamedQuery(name="TbSiaLogin.findAll", query="SELECT t FROM TbSiaLogin t")
public class TbSiaLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "did")
	@org.hibernate.annotations.Type(type="didDef")
	private Integer did;

	@Column(name="cod_password")
	@org.hibernate.annotations.Type(type="strDef")
	private String codPassword;

	@Column(name="cod_postal")
	@org.hibernate.annotations.Type(type="didDef")
	private Integer codPostal;

	@Column(name="des_email")
	@org.hibernate.annotations.Type(type="strDef")
	private String desEmail;

	@Column(name="nom_usuario")
	@org.hibernate.annotations.Type(type="strDef")
	private String nomUsuario;

	@Column(name="num_telefono")
	@org.hibernate.annotations.Type(type="strDef")
	private String numTelefono;

	//bi-directional many-to-one association to TbSiaEmpresa
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="did_empresa", referencedColumnName="did", nullable = false)
	private TbSiaEmpresa tbSiaEmpresa;

	public TbSiaLogin() {
		super();
	}

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getCodPassword() {
		return this.codPassword;
	}

	public void setCodPassword(String codPassword) {
		this.codPassword = codPassword;
	}

	public Integer getCodPostal() {
		return this.codPostal;
	}

	public void setCodPostal(Integer codPostal) {
		this.codPostal = codPostal;
	}

	public String getDesEmail() {
		return this.desEmail;
	}

	public void setDesEmail(String desEmail) {
		this.desEmail = desEmail;
	}

	public String getNomUsuario() {
		return this.nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getNumTelefono() {
		return this.numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public TbSiaEmpresa getTbSiaEmpresa() {
		return this.tbSiaEmpresa;
	}

	public void setTbSiaEmpresa(TbSiaEmpresa tbSiaEmpresa) {
		this.tbSiaEmpresa = tbSiaEmpresa;
	}
}