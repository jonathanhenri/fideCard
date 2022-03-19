package com.fidecard.application.model;

import com.fidecard.application.enuns.StatusUsuario;
import com.fidecard.application.enuns.TipoUsuario;
import com.fidecard.application.model.support.AbstractBaseEntity;
import com.fidecard.application.utils.EncriptaDecriptaAES;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "usuario")
public class Usuario extends AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "login", nullable = false)
	@NotNull
	private byte[] hashLogin;
	
	@Column(name = "hash_senha")
	@NotNull
	private byte[] hashSenha;
	
	@Column(name = "tipo_usuario")
	@Enumerated(EnumType.ORDINAL)
	private TipoUsuario tipoUsuario;
	
	@Column(name = "status_usuario")
	@Enumerated(EnumType.ORDINAL)
	private StatusUsuario statusUsuario;
	
	@Builder
	public Usuario(Long id, String hashLogin, String hashSenha, TipoUsuario tipoUsuario,
				   StatusUsuario statusUsuario) {
		this.id = id;
		EncriptaDecriptaAES aes = new EncriptaDecriptaAES();
		this.hashLogin = aes.encrypt(hashLogin);
		this.hashSenha = aes.encrypt(hashSenha);
		this.tipoUsuario = tipoUsuario;
		this.statusUsuario = statusUsuario;
	}
	
	public String getLoginDescriptografado() {
		EncriptaDecriptaAES aes = new EncriptaDecriptaAES();
		return aes.decrypt(getHashLogin());
	}
	
	public String getSenhaDescriptografada() {
		EncriptaDecriptaAES aes = new EncriptaDecriptaAES();
		return aes.decrypt(getHashSenha());
	}
	
}
