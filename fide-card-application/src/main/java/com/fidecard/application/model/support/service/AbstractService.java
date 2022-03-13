package com.fidecard.application.model.support.service;

import com.fidecard.application.utils.exceptions.CreateException;
import com.fidecard.application.utils.exceptions.DeleteException;
import com.fidecard.application.utils.exceptions.NotFoundException;
import com.fidecard.application.utils.exceptions.UpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Optional;

public abstract class AbstractService<E, R extends CrudRepository<E, Long>> implements Service<E> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);
	
	private R repository;
	
	public AbstractService(R repository) {
		Assert.notNull(repository, "repository");
		this.repository = repository;
	}
	
	@Override
	@Transactional
	public E create(E bean) {
		try {
			beforeCreate(bean);
			LOGGER.trace("creating bean...");
			LOGGER.debug("creating entity: {}", bean);
			E saveBean = getRepository().save(bean);
			LOGGER.debug("creating entity: {} ok!", bean);
			LOGGER.trace("creating bean ok!");
			afterCreate(saveBean);
			return bean;
		} catch (CreateException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CreateException(ex);
		}
	}
	
	protected void beforeCreate(E bean) {
	}
	
	protected void afterCreate(E bean) {
	}
	
	@Override
	@Transactional
	public E update(E bean) {
		try {
			beforeUpdate(bean);
			LOGGER.trace("updating bean...");
			LOGGER.debug("updating entity: {}", bean);
			E updateBean = getRepository().save(bean);
			LOGGER.debug("updating entity: {} ok!", bean);
			LOGGER.trace("updating bean ok!");
			afterUpdate(updateBean);
			return updateBean;
			
		} catch (UpdateException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new UpdateException(ex);
		}
	}
	
	protected void beforeUpdate(E bean) {
	}
	
	protected void afterUpdate(E bean) {
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		try {
			delete(findById(id));
		} catch (NotFoundException e) {
			throw new DeleteException(e);
		}
	}
	
	@Override
	@Transactional
	public void delete(E bean) {
		try {
			LOGGER.debug("deleting entity: {}", bean);
			beforeDelete(bean);
			repository.delete(bean);
			afterDelete(bean);
			LOGGER.debug("deleting entity: {} ok!", bean);
			LOGGER.trace("deleting bean ok!");
			
		} catch (DeleteException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new DeleteException(ex);
		}
	}
	
	protected void beforeDelete(E bean) {
	}
	
	protected void afterDelete(E bean) {
	}
	
	@Override
	@Transactional(readOnly = true)
	public E findById(Long id) {
		Optional<E> current = this.repository.findById(id);
		if (!current.isPresent()) {
			throw new NotFoundException();
		}
		return current.get();
	}
	
	/**
	 * @return a classe que representa o repositório capaz de lidar com as operações do banco
	 */
	protected R getRepository() {
		return repository;
	}
	
	/**
	 * Criado para ser usado nos testes unitarios
	 *
	 * @param repository repository a ser setado
	 */
	public void setRepository(R repository) {
		this.repository = repository;
	}
	
}
