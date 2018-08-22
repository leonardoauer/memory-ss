package com.memory.core.service;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import com.memory.core.model.Bean;

public class CrudService<T extends Bean> implements IService<T> {

	@Override
	public T findOne(Long id) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public List<T> findAll(List<Long> ids) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public List<T> findAll() {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public void update(T entity) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public void update(List<T> entities) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public T save(T entity) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public List<Long> save(List<T> entities) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public void delete(T entity) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public void delete(Long id) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public void delete(List<Long> ids) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}

	@Override
	public boolean exist(Long id) {
		throw new NotImplementedException("You must implement this method in the service implementation");
	}
}
