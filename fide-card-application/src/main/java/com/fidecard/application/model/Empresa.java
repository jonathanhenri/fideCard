package com.fidecard.application.model;

import com.fidecard.application.model.support.AbstractBaseEntity;
import lombok.Builder;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "empresa")
public class Empresa extends AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "razao_social", nullable = false)
	@Size(min = 5, max = 500)
	private String razaoSocial;
	
	@NotNull
	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", foreignKey = @ForeignKey(name = "fk_empresa_endereco"))
	private Endereco endereco;
	
	@Column(name = "responsavel_nome", nullable = false)
	@Size(min = 1, max = 255)
	private String responsavelNome;
	
	@Column(name = "responsavel_telefone")
	@Size(min = 6, max = 30)
	private String responsavelTelefone;
	
	@Column(name = "cnpj")
	@Size(min = 14, max = 20)
	private String cnpj;
	
	@Column(name = "url_logo")
	private String urlLogo;
	
	@Builder
	public Empresa(Long id, String razaoSocial, Endereco endereco, String responsavelNome, String responsavelTelefone,
				   String cnpj) {
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.endereco = endereco;
		this.responsavelNome = responsavelNome;
		this.responsavelTelefone = responsavelTelefone;
		this.cnpj = cnpj;
	}
	
}
