package com.memory.core.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.memory.core.model.Bean;

public class StoreProcedureInsertBuilder {

	private SimpleJdbcCall simpleJdbcCall;
	private String procedureName;
	private String primaryKeyName;
	private MapSqlParameterSource sqlParams;

	public static StoreProcedureInsertBuilder getInstance(SimpleJdbcCall simpleJdbcCall) {
		StoreProcedureInsertBuilder spInsertBuilder = new StoreProcedureInsertBuilder();
		spInsertBuilder.simpleJdbcCall = simpleJdbcCall;
		spInsertBuilder.sqlParams = new MapSqlParameterSource();
		return spInsertBuilder;
	}

	public StoreProcedureInsertBuilder storeProcedure(String procedureName) {
		this.procedureName = procedureName;
		return this;
	}

	public StoreProcedureInsertBuilder primaryKey(String primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
		return this;
	}

	public StoreProcedureInsertBuilder addParam(String columnName, Object value) {
		
		if(StringUtils.isEmpty(columnName) || value == null) {
			throw new IllegalArgumentException("### You must specify column name or object value to use StoreProcedureInsertBuilder ###");
		}
		
		int type = Types.NUMERIC;
		if(value instanceof String) {
			type = Types.VARCHAR;
		} 
		
		sqlParams.addValue(columnName, value, type);
		return this;
	}

	/**
	 * Executes insert operation and returns the generated key
	 * 
	 * @return
	 */
	public Number executeAndReturnKey() {
		Map<String, Object> result = this.initStoreProcedureInsertBuilder().execute(sqlParams);
		return (Number) ((List<?>) result.get(primaryKeyName + "_pk")).get(0);
	}

	/**
	 * Exceutes insert operation and set the generated key to the entity passed
	 * as parameter.
	 * 
	 * @param entity
	 * @return
	 */
	public <T extends Bean> T executeAndReturnKey(T entity) {
		Number primaryKey = this.executeAndReturnKey();
		entity.setId(primaryKey.longValue());
		return entity;
	}

	private SimpleJdbcCall initStoreProcedureInsertBuilder() {

		if (StringUtils.isEmpty(procedureName)) {
			throw new IllegalArgumentException(" ### You must specified the store procedure name to use StoreProcedureInsertBuilder ###");
		}

		if (StringUtils.isEmpty(primaryKeyName)) {
			throw new IllegalArgumentException(" ### You must specified a primary key to use StoreProcedureInsertBuilder ###");
		}

		if (sqlParams == null || MapUtils.isEmpty(sqlParams.getValues())) {
			throw new IllegalArgumentException(" ### You must specified columns to use StoreProcedureInsertBuilder ###");
		}

		simpleJdbcCall.withProcedureName(procedureName);
		
		// Primary key configuration
		simpleJdbcCall.returningResultSet(primaryKeyName + "_pk", new RowMapper<Number>() {

			@Override
			public Number mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(primaryKeyName);
			}
		});
		
		return simpleJdbcCall;
	}
}
