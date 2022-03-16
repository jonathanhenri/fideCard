package com.fidecard.application.model.support;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fidecard.application.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "_uuid")
public abstract class AbstractBaseEntity implements Entityable {
	
	private static final long serialVersionUID = -6227733915976740110L;
	@Version
	private Integer version;
	
	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_criacao_id", foreignKey = @ForeignKey(name = "fk_usuario_criacao"))
	private Usuario usuarioCriacao;
	
	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder().append(this.getClass().getName());
		if (this.getId() != null) {
			hashCodeBuilder.append("{").append("id : ").append(getId()).append(",").append("version : ")
					.append(version).append("}");
		}
		return hashCodeBuilder.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		return new EqualsBuilder().append(this.getId(), ((AbstractBaseEntity) obj).getId())
				.append(this.getVersion(), ((AbstractBaseEntity) obj).getVersion()).isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("version", version).toString();
	}
	
	@Transient
	@Override
	public boolean isNew() {
		return getId() == null;
	}
	
}
