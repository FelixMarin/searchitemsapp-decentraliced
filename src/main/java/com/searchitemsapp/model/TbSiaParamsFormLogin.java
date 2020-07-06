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
 * The persistent class for the tb_sia_params_form_login database table.
 * 
 *  @author Felix Marin Ramirez
 */
@Entity
@Table(name="tb_sia_params_form_login", schema = "sia")
@NamedQuery(name="TbSiaParamsFormLogin.findAll", query="SELECT t FROM TbSiaParamsFormLogin t")
public class TbSiaParamsFormLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "did")
	@org.hibernate.annotations.Type(type="didDef")
	private Integer did;

	@Column(name="param_clave")
	@org.hibernate.annotations.Type(type="strDef")
	private String paramClave;

	@Column(name="param_valor")
	@org.hibernate.annotations.Type(type="strDef")
	private String paramValor;

	//bi-directional many-to-one association to TbSiaUrl
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="did_url", referencedColumnName="did", nullable = false)
	private TbSiaUrl tbSiaUrl;

	public TbSiaParamsFormLogin() {
		super();
	}

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getParamClave() {
		return this.paramClave;
	}

	public void setParamClave(String paramClave) {
		this.paramClave = paramClave;
	}

	public String getParamValor() {
		return this.paramValor;
	}

	public void setParamValor(String paramValor) {
		this.paramValor = paramValor;
	}

	public TbSiaUrl getTbSiaUrl() {
		return this.tbSiaUrl;
	}

	public void setTbSiaUrl(TbSiaUrl tbSiaUrl) {
		this.tbSiaUrl = tbSiaUrl;
	}

}