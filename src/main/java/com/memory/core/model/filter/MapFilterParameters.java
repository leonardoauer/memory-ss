package com.memory.core.model.filter;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class MapFilterParameters {

	private MapSqlParameterSource params = new MapSqlParameterSource();

	public void addValue(String param, Integer value) {
		if (value != null) {
			params.addValue(param, value);
		}
	}

	public void addValue(String param, String value) {
		if (value != null) {
			params.addValue(param, value);
		}
	}

	public MapSqlParameterSource getParams() {
		return this.params;
	}
}
