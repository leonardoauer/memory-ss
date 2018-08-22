package com.memory.core.service.jdbc;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.memory.core.model.Bean;
import com.memory.core.repository.jdbc.JdbcRepository;
import com.memory.core.service.IService;
import com.memory.core.util.BeanUtils;

public abstract class CrudJdbcService<T extends Bean> implements IService<T> {
	
	protected abstract JdbcRepository<T> getJdbcRepository();

	@Override
	public T findOne(Long id) {
		
		if(id == null || id.intValue() == 0) {
			return null;
		}
		
		return this.getJdbcRepository().findOne(id);
	}

	@Override
	public List<T> findAll(List<Long> ids) {
		return this.getJdbcRepository().findAll(ids);
	}

	@Override
	public List<T> findAll() {
		return this.getJdbcRepository().findAll();
	}

	@Override
	public void update(T entity) {
		
		if(BeanUtils.isNull(entity)) {
			return;
		}
		
 		this.getJdbcRepository().update(entity);		
	}

	@Override
	public void update(List<T> entities) {
		
		if(BeanUtils.isNull(entities)) {
			return;
		}
		
		this.getJdbcRepository().update(entities);
	}

	@Override
	public T save(T entity) {
		
		if(entity == null) {
			return null;
		}
		
		return this.getJdbcRepository().save(entity);
	}

	@Override
	public List<Long> save(List<T> entities) {
		
		if(CollectionUtils.isEmpty(entities)) {
			return null;
		}
		
		return this.getJdbcRepository().save(entities);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Long id) {

		// Discover the class type
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		Class<T> genericClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

		// Create new bean
		T entity = BeanUtils.newBean(id, genericClass);
		
		//delete the bean
		this.delete(entity);
	}

	@Override
	public void delete(T entity) {
		
		if(BeanUtils.isNull(entity)) {
			return;
		}
		
		this.getJdbcRepository().delete(entity);
	}

	@Override
	public void delete(List<Long> ids) {
		
		if(CollectionUtils.isEmpty(ids)) {
			return;
		}
		
		this.getJdbcRepository().delete(ids);
	}

	@Override
	public boolean exist(Long id) {
		
		if(id == null || id.intValue() == 0) {
			return false;
		}
		
		return this.getJdbcRepository().exist(id);
	}
}
