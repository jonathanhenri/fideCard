package com.fidecard.common.usuario;

import com.fidecard.common.AbstractDto;
import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "Usuário")
public class UsuarioDto extends AbstractDto {
	
	private String hashLogin;
	private String hashSenha;
	
}
