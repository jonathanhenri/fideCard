package com.fidecard.application.model.regraCartao;

import com.fidecard.application.enuns.TipoRegraCartao;
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
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
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
	private LocalDateTime data;
	
	
	@Column(name = "tipo_regra_cartao", nullable = false)
	private TipoRegraCartao tipoRegraCartao;
	
}
