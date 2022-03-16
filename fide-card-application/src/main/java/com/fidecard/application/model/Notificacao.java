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
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "notificacao")
public class Notificacao extends AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "entregue")
	@NotNull
	private boolean entregue;
	
	@NotNull
	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_notificacao_id", foreignKey = @ForeignKey(name = "fk_usuario_notificacao"))
	private Usuario usuarioNotificacao;
}
