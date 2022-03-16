package com.fidecard.application.model;

import com.fidecard.application.model.support.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cliente")
public class Cliente extends AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "hash_cpf", nullable = false)
	@NotBlank(message = "Hash senha é obrigatório")
	private String hashCpf;
	
	@NotBlank(message = "Hash senha é obrigatório")
	@Column(name = "hash_senha", nullable = false)
	private String hashSenha;
	
	@NotBlank(message = "Nome é obrigatório")
	@Column(name = "nome", nullable = false)
	@Size(max = 500, message = "Nome maior que 500 caracteres")
	private String nome;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimoAcesso;
	
	@NotNull
	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", foreignKey = @ForeignKey(name = "fk_cliente_endereco"))
	private Endereco endereco;
	
}
