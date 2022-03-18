package com.fidecard.common.auditoria;

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
@ApiModel(description = "Auditoria")
public class AuditoriaDto extends AbstractDto {
	
	@ApiModelProperty(name = "Nome da tabela")
	private String tableName;
	
	@ApiModelProperty(name = "Json da auditoria")
	private String jsonTexto;
	
}
