package com.fidecard.application.services.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomRepository<E> extends CrudRepository<E, Long> {
}