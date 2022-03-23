package com.fidecard.common.usuario;

import com.fidecard.common.AbstractDto;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Usuário")
public class UsuarioDto extends AbstractDto {
	
	@NotEmpty
	private String login;
	
	@NotEmpty
	private String senha;
	
	private List<String> permissoes;
	
	public List<String> getPermissoes() {
		return Optional.ofNullable(permissoes).orElseGet(ArrayList::new);
	}
	
	@Builder
	public UsuarioDto(Long id, String login, String senha, List<String> permissoes) {
		super(id);
		this.login = login;
		this.senha = senha;
		this.permissoes = permissoes;
	}
	
}
