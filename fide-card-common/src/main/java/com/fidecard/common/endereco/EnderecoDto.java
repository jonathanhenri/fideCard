package com.fidecard.common.endereco;

import com.fidecard.common.AbstractDto;
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
public class EnderecoDto extends AbstractDto {
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private Integer codigoMunicipio;
	private String nomeMunicipio;
	private String cep;
	private String codigoPais;
	private String pais;
	private String telefone;
	
	@ApiModelProperty(example = "UF")
	private String uf;
	
}
