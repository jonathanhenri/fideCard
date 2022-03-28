package com.fidecard.common;

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
public class AbstractDto implements Dto {
	
	// Para models que precise buscar algo no banco
	@ApiModelProperty(name = "ID Caso exista")
	private Long id;
	
}
