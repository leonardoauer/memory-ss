
package com.memory.app.model.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.memory.core.model.Bean;
import com.memory.core.util.BeanUtils;

public abstract class BeanUI<E extends Bean> implements IBeanUI<Long> {
	
	private static final Log log = LogFactory.getLog(BeanUI.class);
	
	private static final String UI_SUBFIX = "UI";
	private static final String UI_PACKAGE = "com.memory.app.model.ui";
	private static final String DOT = ".";

	private Long id;
	
	public abstract BeanUI<E> mapModeltoUI(E bean);
	
	@SuppressWarnings({ "unchecked" })
	public static <E extends Bean, T extends BeanUI<E>> T modeltoUI(E bean) {
		
		if(BeanUtils.isNull(bean)) {
			return null;
		}
		
		T newInstance = createBeanUIInstance(bean);
		return (T) newInstance.mapModeltoUI(bean);
	}
	
	@SuppressWarnings({ "unchecked" })
	public static <E extends Bean, T extends BeanUI<E>> List<T> modeltoUI(List<E> beans) {
		
		if(BeanUtils.isNull(beans)) {
			return null;
		}
		
		T newInstance = createBeanUIInstance(beans.get(0));
		
		// Create the memories array list
		List<T> beansUI = new ArrayList<>(beans.size());
		for (E bean : beans) {
			beansUI.add((T) newInstance.mapModeltoUI(bean));
		}
		
		return beansUI;
	}
	
	public static <E extends Bean, T extends BeanUI<E>> Set<T> modeltoUI(Set<E> beans) {
		
		if(BeanUtils.isNull(beans)) {
			return null;
		}
		
		List<T> beansUI = modeltoUI(new ArrayList<>(beans));
		return new HashSet<>(beansUI);
	}
	
	@SuppressWarnings("unchecked")
	private static <E extends Bean, T extends BeanUI<E>> T createBeanUIInstance(E bean) {
		
		T newInstance = null;
		String className = null;
		
		try {
			
			className = discoverUiClassName(bean);
			newInstance = (T) Class.forName(className).newInstance();
			
		} catch (Exception e) {
			log.error("#### Fatal error, it was not possible to instantiate the UI Bean: " + className + " ####");
			log.error("#### Is your UI Bean class name correct ? ####");
		}
		
		return newInstance;
	}
	
	private static <E extends Bean> String discoverUiClassName(E bean) {
		return UI_PACKAGE + DOT + bean.getClass().getSimpleName() + UI_SUBFIX;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
