package com.memory.core.repository.jdbc;

import com.memory.core.model.Bean;

public interface JdbcRepository<T extends Bean> extends CrudFunctions<Long, T> {

}
