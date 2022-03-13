package com.fidecard.application.model.support.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomRepository<E> extends CrudRepository<E, Long> {
}