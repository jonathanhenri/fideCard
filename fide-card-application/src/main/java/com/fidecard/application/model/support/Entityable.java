package com.fidecard.application.model.support;

import org.springframework.data.domain.Persistable;
import java.io.Serializable;

public interface Entityable extends Persistable<Long>, Serializable {
	
	Long getId();
	
	void setId(Long id);
	
}
