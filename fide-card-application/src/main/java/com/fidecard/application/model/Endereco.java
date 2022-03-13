package com.fidecard.application.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Endereco")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 4581901753438097803L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
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
}
