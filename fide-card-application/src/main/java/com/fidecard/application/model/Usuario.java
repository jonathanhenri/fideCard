package com.fidecard.application.model;

import com.fidecard.application.enuns.StatusUsuario;
import com.fidecard.application.enuns.TipoUsuario;
import com.fidecard.application.model.support.AbstractBaseEntity;
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
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
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
	private String hashLogin;
	
	@Column(name = "hash_senha")
	@NotNull
	private String hashSenha;
	
	@Column(name = "tipo_usuario")
	private TipoUsuario tipoUsuario;
	
	@Column(name = "status_usuario")
	private StatusUsuario statusUsuario;
}
