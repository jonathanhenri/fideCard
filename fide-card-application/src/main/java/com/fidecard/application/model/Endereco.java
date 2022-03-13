package com.fidecard.application.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.io.Serializable;

@MappedSuperclass
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 4581901753438097803L;
	
	@Size(min = 2, max = 60)
	@Column(name = "logradouro", nullable = false)
	private String logradouro;
	
	@Size(min = 1, max = 60)
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@Size(min = 1, max = 60)
	@Column(name = "complemento")
	private String complemento;
	
	@Size(min = 2, max = 60)
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "codigo_municipio")
	private Integer codigoMunicipio;
	
	@Size(min = 2, max = 60)
	@Column(name = "nome_municipio", nullable = false)
	private String nomeMunicipio;
	
	@Size(min = 2, max = 2)
	@Column(name = "uf", nullable = false)
	private String uf;
	
	@Column(name = "cep", nullable = false)
	private String cep;
	
	@Column(name = "codigo_pais")
	private String codigoPais;
	
	@Column(name = "pais")
	private String pais;
	
	@Column(name = "telefone")
	private String telefone;
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logadouro) {
		this.logradouro = logadouro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}
	
	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String fone) {
		this.telefone = fone;
	}
	
}
