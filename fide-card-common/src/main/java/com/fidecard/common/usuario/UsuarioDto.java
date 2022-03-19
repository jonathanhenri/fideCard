package com.fidecard.common.usuario;

import com.fidecard.common.AbstractDto;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Usuário")
public class UsuarioDto extends AbstractDto {
	
	private String hashLogin;
	private String hashSenha;
	
	private List<String> permissoes;
	
	public List<String> getPermissoes() {
		return Optional.ofNullable(permissoes).orElseGet(ArrayList::new);
	}
	
	@Builder
	public UsuarioDto(String hashLogin, String hashSenha, List<String> permissoes) {
		this.hashLogin = hashLogin;
		this.hashSenha = hashSenha;
		this.permissoes = permissoes;
	}
	
}
