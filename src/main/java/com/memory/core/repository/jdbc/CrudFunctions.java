package com.memory.core.repository.jdbc;

import java.io.Serializable;
import java.util.List;

import com.memory.core.model.IBean;

/**
 * Basic functions of persisting storage. The acronym CRUD refers to all of the
 * major functions that are implemented in relational database applications.
 * 
 * Any class needing Basic CRUD functions should implements this interface.
 * 
 * @author lEo
 * @param <T>
 */
public interface CrudFunctions<ID extends Serializable, T extends IBean<ID>> {

	T findOne(ID id);

	List<T> findAll(List<ID> ids);

	List<T> findAll();
	
	void update(T entity);

	void update(List<T> entities);

	T save(T entity);

	List<ID> save(List<T> entities);

	void delete(T entity);
	
	void delete(ID id);

	void delete(List<ID> ids);
	
	boolean exist(ID id);
}
