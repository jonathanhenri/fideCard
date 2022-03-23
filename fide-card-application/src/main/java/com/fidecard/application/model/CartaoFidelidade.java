package com.fidecard.application.model;

import com.fidecard.application.enuns.StatusCartao;
import com.fidecard.application.model.regraCartao.RegraCartao;
import com.fidecard.application.model.support.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cartao_fidelidade")
public class CartaoFidelidade extends AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@NotNull
	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_id", foreignKey = @ForeignKey(name = "fk_cartao_empresa"))
	private Empresa empresa;
	
	@NotNull
	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_cartao_cliente"))
	private Cliente cliente;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status_cartao")
	private StatusCartao statusCartao;
	
	@NotNull
	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "layout_cartao_id", foreignKey = @ForeignKey(name = "fk_cartao_layout_cartao"))
	private LayoutCartao layoutCartao;
	
	@Builder
	public CartaoFidelidade(Long id, Empresa empresa, Cliente cliente, StatusCartao statusCartao,
							LayoutCartao layoutCartao) {
		this.id = id;
		this.empresa = empresa;
		this.cliente = cliente;
		this.statusCartao = statusCartao;
		this.layoutCartao = layoutCartao;
	}
	
}
