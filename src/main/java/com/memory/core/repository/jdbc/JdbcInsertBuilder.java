package com.memory.core.repository.jdbc;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.memory.core.model.Bean;

public class JdbcInsertBuilder {

	private SimpleJdbcInsert simpleJdbcInsert;
	private String tableName;
	private String primaryKey;
	private Map<String, Object> parameters;

	public static JdbcInsertBuilder getInstance(SimpleJdbcInsert simpleJdbcInsert) {
		JdbcInsertBuilder jdbcInsertBuilder = new JdbcInsertBuilder();
		jdbcInsertBuilder.simpleJdbcInsert = simpleJdbcInsert;
		jdbcInsertBuilder.parameters = new HashMap<String, Object>();
		return jdbcInsertBuilder;
	}

	public JdbcInsertBuilder table(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public JdbcInsertBuilder primaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
		return this;
	}

	public JdbcInsertBuilder addParam(String columnName, Object value) {
		this.parameters.put(columnName, value);
		return this;
	}

	/**
	 * Executes insert operation and returns the generated key
	 * 
	 * @return
	 */
	public Number executeAndReturnKey() {
		return this.initJdbcInsertBuilder().executeAndReturnKey(parameters);
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

	private SimpleJdbcInsert initJdbcInsertBuilder() {

		if (StringUtils.isEmpty(tableName)) {
			throw new IllegalArgumentException(" ### You must specified a table name to use JdbcInsertBuilder ###");
		}

		if (StringUtils.isEmpty(primaryKey)) {
			throw new IllegalArgumentException(" ### You must specified a primary key to use JdbcInsertBuilder ###");
		}

		if (MapUtils.isEmpty(parameters)) {
			throw new IllegalArgumentException(" ### You must specified columns to use JdbcInsertBuilder ###");
		}

		String[] columnNames = parameters.keySet().toArray(new String[parameters.size()]);

		simpleJdbcInsert.withTableName(tableName);
		simpleJdbcInsert.usingGeneratedKeyColumns(primaryKey);
		simpleJdbcInsert.usingColumns(columnNames);

		return simpleJdbcInsert;
	}
}
