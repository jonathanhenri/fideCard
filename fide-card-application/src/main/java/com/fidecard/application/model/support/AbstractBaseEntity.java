package com.fidecard.application.model.support;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "_uuid")
public abstract class AbstractBaseEntity implements Entityable {
	
	private static final long serialVersionUID = -6227733915976740110L;
	@Version
	private Integer version;
	
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
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
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
