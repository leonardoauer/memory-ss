package com.memory.core.repository.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUpdateBuilder {

	private JdbcTemplate jdbcTemplate;
	private String tableName;
	private String whereIdParam;
	private Object whereIdValue;
	private Map<String, Object> parameters;

	public static JdbcUpdateBuilder getInstance(JdbcTemplate jdbcTemplate) {
		JdbcUpdateBuilder jdbcUpdateBuilder = new JdbcUpdateBuilder();
		jdbcUpdateBuilder.jdbcTemplate = jdbcTemplate;
		jdbcUpdateBuilder.parameters = new HashMap<String, Object>();
		return jdbcUpdateBuilder;
	}

	public void update() {
		
		if (StringUtils.isEmpty(tableName)) {
			throw new IllegalArgumentException(" ### You must specified a table name to use JdbcUpdateBuilder ###");
		}
		
		if (StringUtils.isEmpty(whereIdParam) || whereIdValue == null) {
			throw new IllegalArgumentException(" ### You must specified the where id clause to use JdbcUpdateBuilder ###");
		}
		
		if (MapUtils.isEmpty(parameters)) {
			throw new IllegalArgumentException(" ### You must specified columns and parameters to use JdbcUpdateBuilder ###");
		}		

		StringBuilder query = new StringBuilder();
		query.append(" UPDATE ");
		query.append(this.tableName);
		query.append(" SET ");

		List<Object> paramsForQuery = new ArrayList<>();
		for (Entry<String, Object> next : parameters.entrySet()) {

			String column = next.getKey();
			Object value = next.getValue();

			query.append(column);
			query.append(" = ?, ");
			paramsForQuery.add(value);
		}

		query.deleteCharAt(query.length() - 2);

		query.append(" WHERE ");
		query.append(whereIdParam);
		query.append(" = ? ");
		paramsForQuery.add(whereIdValue);

		jdbcTemplate.update(query.toString(), paramsForQuery.toArray());
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public JdbcUpdateBuilder addParam(String columnName, Object value) {
		this.parameters.put(columnName, value);
		return this;
	}

	public JdbcUpdateBuilder addWhereId(String whereIdParam, Object value) {
		this.whereIdParam = whereIdParam;
		this.whereIdValue = value;
		return this;
	}
}
