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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pronto_cartao")
public class PontoCartao extends AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "valor", nullable = false)
	@Positive
	private Double valor;
	
	@Column(name = "identificador_unico", nullable = false)
	private String identificadorUnico;
	
	@Column(name = "data_pontuacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPontuacao;
	
	@NotNull
	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cartao_fidelidade_id", foreignKey = @ForeignKey(name = "fk_ponto_cartao"))
	private CartaoFidelidade cartaoFidelidade;
	
	
	@Builder
	public PontoCartao(Long id, Double valor, String identificadorUnico, Date dataPontuacao,
					   CartaoFidelidade cartaoFidelidade) {
		this.id = id;
		this.valor = valor;
		this.identificadorUnico = identificadorUnico;
		this.dataPontuacao = dataPontuacao;
		this.cartaoFidelidade = cartaoFidelidade;
	}
	
}
