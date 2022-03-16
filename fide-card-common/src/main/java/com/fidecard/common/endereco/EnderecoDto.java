package com.fidecard.common.endereco;

import com.fidecard.common.Dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Endereço")
public class EnderecoDto implements Dto {
	
	@ApiModelProperty(notes = "Logradouro")
	private String logradouro;
	
	@ApiModelProperty(notes = "Numero do Endereço")
	private String numero;
	
	@ApiModelProperty(notes = "Complemento")
	private String complemento;
	
	@ApiModelProperty(notes = "Bairro")
	private String bairro;
	
	@ApiModelProperty(notes = "Codigo Municipio")
	private Integer codigoMunicipio;
	
	@ApiModelProperty(notes = "Nome Municipio")
	private String nomeMunicipio;
	
	@ApiModelProperty(notes = "UF")
	private String uf;
	
	@ApiModelProperty(notes = "Cep")
	private String cep;
	
	@ApiModelProperty(notes = "Codigo Pais")
	private String codigoPais;
	
	@ApiModelProperty(notes = "Pais")
	private String pais;
	
	@ApiModelProperty(notes = "Telefone Residencial")
	private String telefone;
	
}
