package com.memory.core.repository.jdbc;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import com.memory.core.model.Bean;

/**
 * Extended version of SimpleJDBCRepository. JDBCRepository contains all CRUD
 * Basic operations
 * 
 * @author lEo
 * @param <T>
 */
public abstract class JdbcSupport<T extends Bean> extends JdbcFunctions<T> implements JdbcRepository<T> {

	@Override
	public T findOne(Long id) {
		throw new NotImplementedException("You must implement the findOne() method in your repository implementation");
	}

	@Override
	public List<T> findAll(List<Long> ids) {
		throw new NotImplementedException("You must implement the findAll() method in your repository implementation");
	}

	@Override
	public List<T> findAll() {
		throw new NotImplementedException("You must implement the findAll() method in your repository implementation");
	}

	@Override
	public void update(T entity) {
		throw new NotImplementedException("You must implement the update() method in your repository implementation");
	}

	@Override
	public void update(List<T> entity) {
		throw new NotImplementedException("You must implement the update() method in your repository implementation");
	}

	@Override
	public T save(T entity) {
		throw new NotImplementedException("You must implement the save() method in your repository implementation");
	}

	@Override
	public List<Long> save(List<T> entities) {
		throw new NotImplementedException("You must implement the save() list method in your repository implementation");
	}

	@Override
	public void delete(T entity) {
		throw new NotImplementedException("You must implement the delete() method in your repository implementation");
	}

	@Override
	public void delete(Long entity) {
		throw new NotImplementedException("You must implement the delete() method in your repository implementation");
	}

	@Override
	public void delete(List<Long> ids) {
		throw new NotImplementedException("You must implement the delete() list method in your repository implementation");
	}

	@Override
	public boolean exist(Long id) {
		throw new NotImplementedException("You must implement the exist() method in your repository implementation");
	}
	
	public boolean exist(Integer id, String tableName, String column) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT COUNT(*) FROM ");
		sb.append(tableName);
		sb.append(" WHERE ");
		sb.append(column);
		sb.append(" = ? ");
		return getJdbcTemplate().queryForObject(sb.toString(), Integer.class, id) > 0;		
	}
}
