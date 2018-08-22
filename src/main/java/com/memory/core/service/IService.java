package com.memory.core.service;

import com.memory.core.model.Bean;
import com.memory.core.repository.jdbc.CrudFunctions;

public interface IService<T extends Bean> extends CrudFunctions<Long, T>{

}
