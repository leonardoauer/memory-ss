package com.memory.core.repository.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * Most Basic JDBC operations
 * 
 * @author lauer
 */
public abstract class JdbcDataSource {

	private DataSource dataSource;

	@Autowired
	protected void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected DataSource getDataSource() {
		return this.dataSource;
	}

	protected JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(this.dataSource);
	}

	protected SimpleJdbcInsert getSimpleJdbcInsert() {
		return new SimpleJdbcInsert(this.dataSource);
	}

	protected JdbcUpdateBuilder getJdbcUpdateBuilder() {
		return JdbcUpdateBuilder.getInstance(getJdbcTemplate());
	}

	protected JdbcInsertBuilder getJdbcInsertBuilder() {
		return JdbcInsertBuilder.getInstance(getSimpleJdbcInsert());
	}

	protected StoreProcedureInsertBuilder getStprcInsertBuilder() {
		return StoreProcedureInsertBuilder.getInstance(getSimpleJdbcCall());
	}

	protected SimpleJdbcCall getSimpleJdbcCall() {
		return new SimpleJdbcCall(this.dataSource);
	}

	protected SimpleJdbcCall getSimpleJdbcCall(String procedureName) {
		return new SimpleJdbcCall(this.dataSource).withProcedureName(procedureName);
	}
}
