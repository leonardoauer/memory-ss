package com.memory.core.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class DtoBuilder {
	
	
	private static final Log log = LogFactory.getLog(DtoBuilder.class);
	
	private static final String DTO_SUBFIX = "Dto";
	private static final String DTO_PACKAGE = "com.memory.app.rest.dto.";
	
	public static <T extends Serializable, S extends IDto<T>> S createDto(T bean) {
		
		if(bean == null) {
			return null;
		}
		
		String dtoClassSimpleName = DTO_PACKAGE + bean.getClass().getSimpleName() + DTO_SUBFIX;
		S dtoInstance = newInstance(dtoClassSimpleName);
		return dtoInstance.build(bean);
	}

	public static <T extends Serializable, S extends IDto<T>> List<S> createDto(List<T> beans) {
		
		if(CollectionUtils.isEmpty(beans)) {
			return null;
		}
		
		String dtoClassSimpleName = DTO_PACKAGE + beans.get(0).getClass().getSimpleName() + DTO_SUBFIX;
		List<S> dtos = new ArrayList<>(beans.size());
		
		for (T bean : beans) {
			S dtoInstance = newInstance(dtoClassSimpleName);
			dtos.add(dtoInstance.build(bean));
		}
		
		return dtos;
	}
	
	public static <T extends Serializable, S extends IDto<T>> Page<S> createDto(Page<T> page) {
		
		List<T> beans = page.getContent();
		
		if(CollectionUtils.isEmpty(beans)) {
			return null;
		}
		
		String dtoClassSimpleName = DTO_PACKAGE + beans.get(0).getClass().getSimpleName() + DTO_SUBFIX;
		
		List<S> tempList = new ArrayList<>();
		
		for (Iterator<T> iterator = beans.iterator(); iterator.hasNext();) {
			T bean = iterator.next();
			S dtoInstance = newInstance(dtoClassSimpleName);
			tempList.add(dtoInstance.build(bean));
		}

		Page<S> dtos = new PageImpl<>(tempList, new PageRequest(page.getNumber(), page.getSize(), page.getSort()), 
				page.getTotalElements());
		
		return dtos;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Serializable, S extends IDto<T>> S newInstance(String dtoClassSimpleName) {
		
		S newInstance = null;
		
		try {
			newInstance = (S) Class.forName(dtoClassSimpleName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			log.error("#### Fatal error, it was not possible to instantiate DTO: " + dtoClassSimpleName + " ####");
			log.error("#### Is your Dto class name correct ? ####");
		}
		
		return newInstance;
	}
	
	/**
	 * Returns the ids of every dto
	 * 
	 * @param dtos
	 * @return
	 */
	public static <T extends Serializable, S extends IDto<T>> List<Long> listIds(List<S> dtos) {
		
		if(CollectionUtils.isEmpty(dtos)) {
			return null;
		}
		
		List<Long> ids = new ArrayList<>(dtos.size());
		for (S dto : dtos) {
			ids.add(dto.getId());
		}
		
		return ids;
	}
	
	public static <T> PageImpl<T> emptyPage(Pageable pageable) {
		List<T> dtos = new ArrayList<>();
		return new PageImpl<T>(dtos, pageable, 1L);		
	}	
}
