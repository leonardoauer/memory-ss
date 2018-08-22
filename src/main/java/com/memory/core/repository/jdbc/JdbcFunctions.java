package com.memory.core.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.memory.core.model.Bean;
import com.memory.core.model.filter.MapFilterParameters;

/**
 * Utility methods for JDBC Basic operations.
 * 
 * @author lEo
 *
 * @param <T>
 */
public abstract class JdbcFunctions<T extends Bean> extends JdbcDataSource {

	/**
	 * Same as queryForObject() method of jdbcTemplate, with the difference that
	 * queryForObjectNull allows the query result to be empty. In case the query
	 * does not return result, the method return a NULL result.
	 * 
	 * @param sql
	 * @param args
	 * @param rowMapper
	 * @return
	 * @throws DataAccessException
	 */
	protected T queryForObjectNull(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
		List<T> resultList = getJdbcTemplate().query(sql, args, rowMapper);
		return getFirstFromList(resultList);
	}

	/**
	 * Supports null value as result.
	 * 
	 * @param sql
	 * @param args
	 * @param clazz
	 * @return
	 * @throws DataAccessException
	 */
	protected <E> E queryForObjectNull(String sql, Object[] args, Class<E> clazz) throws DataAccessException {
		E result = null;

		try {

			result = getJdbcTemplate().queryForObject(sql, args, clazz);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return result;
	}

	/**
	 * Supports null value as result.
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 * @throws DataAccessException
	 */
	protected <E> E queryForObjectNull(String sql, Class<E> clazz) throws DataAccessException {
		E result = null;

		try {

			result = getJdbcTemplate().queryForObject(sql, null, clazz);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return result;
	}

	/**
	 * Call the specified store procedure. It is expected the store procedure to
	 * return only one value, otherwise it throws exception;
	 * 
	 * @param procedureName
	 * @param params
	 * @param rowMapper
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T storeProcedureForObject(String procedureName, SqlParameterSource params, RowMapper<T> rowMapper) {
		SimpleJdbcCall simpleJdbcCall = getSimpleJdbcCall(procedureName);
		simpleJdbcCall.returningResultSet(procedureName + "_list_esult", rowMapper);

		Map<String, Object> m = simpleJdbcCall.execute(params);
		List<T> resultList = (List<T>) m.get(procedureName + "_list_esult");
		return getFirstFromList(resultList);
	}

	@SuppressWarnings("unchecked")
	protected List<T> storeProcedureForList(String procedureName, SqlParameterSource params, RowMapper<T> rowMapper) {
		SimpleJdbcCall simpleJdbcCall = getSimpleJdbcCall(procedureName);
		simpleJdbcCall.returningResultSet(procedureName + "_list_esult", rowMapper);

		Map<String, Object> m = simpleJdbcCall.execute(params);
		return (List<T>) m.get(procedureName + "_list_esult");
	}

	@SuppressWarnings("unchecked")
	protected List<T> storeProcedureForList(String procedureName, MapFilterParameters params, RowMapper<T> rowMapper) {
		SimpleJdbcCall simpleJdbcCall = getSimpleJdbcCall(procedureName);
		simpleJdbcCall.returningResultSet(procedureName + "_list_esult", rowMapper);

		Map<String, Object> m = simpleJdbcCall.execute(params.getParams());
		return (List<T>) m.get(procedureName + "_list_esult");
	}

	@SuppressWarnings("unchecked")
	protected List<T> storeProcedureForList(String procedureName, RowMapper<T> rowMapper) {
		SimpleJdbcCall simpleJdbcCall = getSimpleJdbcCall(procedureName);
		simpleJdbcCall.returningResultSet(procedureName + "_list_esult", rowMapper);

		Map<String, Object> m = simpleJdbcCall.execute(new HashMap<String, Object>(0));
		return (List<T>) m.get(procedureName + "_list_esult");
	}

	protected T storeProcedureForUpdate(T entity, String procedureName, SqlParameterSource params, String primaryKeyName) {
		SimpleJdbcCall simpleJdbcCall = getSimpleJdbcCall(procedureName);

		// Primary key configuration
		simpleJdbcCall.returningResultSet(primaryKeyName + "_pk", new RowMapper<Number>() {

			@Override
			public Number mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong(primaryKeyName);
			}
		});

		Map<String, Object> result = simpleJdbcCall.execute(params);
		entity.setId((Long) ((List<?>) result.get(primaryKeyName + "_pk")).get(0));
		return entity;
	}

	/**
	 * Get first value from a list that must not be null. If list has more than
	 * one value, it throws IllegalStateException, because this method serves to
	 * guarante that list must contain only one value!!
	 * 
	 * @param resultList
	 * @return
	 */
	private T getFirstFromList(List<T> resultList) {

		T result = null;

		if (resultList.size() == 1) {
			result = resultList.get(0);
		} else if (resultList.size() > 1) {
			throw new IllegalStateException("### Result has more than one value when expecting only one! ###");
		}

		return result;
	}
}
