package com.memory.core.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public abstract class Dto<T extends Serializable> implements IDto<T> {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	protected abstract <S extends IDto<T>> S createDto(T t);
	
	protected Long id;
	
	@Override
	public <S extends IDto<T>> S build(T bean) {
		
		if(bean == null) {
			return null;
		}
		
		return createDto(bean);
	}
	
	@Override
	public <S extends IDto<T>> List<S> build(List<T> beans) {
		
		if(CollectionUtils.isEmpty(beans)) {
			return null;
		}
		
		List<S> dtos = new ArrayList<>(beans.size());
		for (T bean : beans) {
			dtos.add(this.build(bean));
		}
		
		return dtos;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}	
}
