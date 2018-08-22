package com.memory.core.rest.dto;

import java.io.Serializable;
import java.util.List;

import com.memory.core.model.IBean;

public interface IDto<T extends Serializable> extends Serializable, IBean<Long> {
	
	<S extends IDto<T>> S build(T t);
	
	<S extends IDto<T>> List<S> build(List<T> t);
}
