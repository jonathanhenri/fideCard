package com.fidecard.application.model.regraCartao;

import com.fidecard.application.enuns.TipoRegraCartao;
import com.fidecard.application.model.CartaoFidelidade;
import com.fidecard.application.model.support.AbstractBaseEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Positive;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "regra_cartao")
public class RegraCartao extends AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "valor")
	@Positive
	private Double valor;
	
	@Column(name = "data")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(name = "tipo_regra_cartao", nullable = false)
	private TipoRegraCartao tipoRegraCartao;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "cartao_fidelidade_id")
	private CartaoFidelidade cartaoFidelidade;
	
	@Builder
	public RegraCartao(Long id, Double valor, Date data, TipoRegraCartao tipoRegraCartao,
					   CartaoFidelidade cartaoFidelidade) {
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.tipoRegraCartao = tipoRegraCartao;
		this.cartaoFidelidade = cartaoFidelidade;
	}
	
}
