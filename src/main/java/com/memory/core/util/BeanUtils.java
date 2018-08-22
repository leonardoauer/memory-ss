package com.memory.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.memory.core.model.Bean;
import com.memory.core.model.User;

public class BeanUtils {
	
	public static <T extends Bean> T newBean(Long id, Class<T> clazz) {
		T newInstance = null;
		
		try {
			newInstance = clazz.newInstance();
			newInstance.setId(id);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return newInstance;
	}
	
	public static User newUser(Long id) {
		return newBean(id, User.class);
	}

	/**
	 * If the bean is null or the id equals zero return true
	 * 
	 * @param bean
	 * @return
	 */
	public static boolean isNull(Bean bean) {

		boolean isNull = false;

		if (bean == null || bean.getId() == null || bean.getId() == 0) {
			isNull = true;
		}

		return isNull;
	}

	/**
	 * If any bean contained in the list is null or any of the id equals zero
	 * return true
	 * 
	 * @param bean
	 * @return
	 */
	public static <T extends Bean> boolean isNull(Collection<T> beans) {

		if (CollectionUtils.isEmpty(beans)) {
			return true;
		}

		boolean oneOfThemIsNull = false;

		for (Bean bean : beans) {
			if (isNull(bean)) {
				oneOfThemIsNull = true;
				break;
			}
		}

		return oneOfThemIsNull;
	}

	/**
	 * If any of the beans are null, or any of the ids are zero it returns true.
	 * 
	 * @param beans
	 * @return
	 */
	public static boolean isNull(Bean... beans) {

		boolean oneOfThemIsNull = false;

		if (ArrayUtils.isEmpty(beans)) {
			return oneOfThemIsNull;
		}

		for (Bean bean : beans) {

			if (isNull(bean)) {
				oneOfThemIsNull = true;
				break;
			}
		}

		return oneOfThemIsNull;
	}
	
	/**
	 * If the beans are not null, or the ids are not zero, it returns true.
	 * 
	 * @param beans
	 * @return
	 */
	public static boolean isNotNull(Bean... beans) {
		return !isNull(beans);
	}

	/**
	 * If the bean is not null, or the id is distinct than zero return true
	 * 
	 * @param bean
	 * @return
	 */
	public static boolean isNotNull(Bean bean) {
		return !isNull(bean);
	}

	/**
	 * If the collection contains the param id, it returns true. This id refers
	 * to the Bean id.
	 * 
	 * @param beans
	 * @param beanId
	 * @return
	 */
	public static <T extends Bean> boolean containsId(Collection<T> beans, int beanId) {
		Map<Long, T> beansMap = toMap(beans);
		return beansMap.containsKey(beanId);
	}

	/**
	 * Converts a Bean collection into a Map;
	 * 
	 * @param beans
	 * @return
	 */
	public static <T extends Bean> Map<Long, T> toMap(Collection<T> beans) {

		if (CollectionUtils.isEmpty(beans)) {
			return null;
		}

		Map<Long, T> beansMap = new HashMap<Long, T>();

		for (T bean : beans) {
			beansMap.put(bean.getId(), bean);
		}

		return beansMap;
	}

	/**
	 * Return zero value if the bean is null or the id equals zero
	 * 
	 * @param bean
	 * @return
	 */
	public static Long zeroWhenNull(Bean bean) {
		return isNotNull(bean) ? bean.getId() : 0;
	}

	/**
	 * Adds list2 into list1, only if list1 is not null, and if list2 is not
	 * null. This method do not add duplicated Bean values.
	 * 
	 * @param list1
	 * @param list2
	 */
	public static <T extends Bean> void addAllNotDuplicate(List<T> list1, List<T> list2) {

		if (CollectionUtils.isEmpty(list1) || CollectionUtils.isEmpty(list2)) {
			return;
		}

		for (T bean : list2) {
			if (!list1.contains(bean)) {
				list1.add(bean);
			}
		}
	}
	
	public static <T extends Bean> List<T> listFromBean(T bean) {
		
		if(isNull(bean)) {
			return null;
		}
		
		List<T> newList = new ArrayList<>(1);
		newList.add(bean);
		return newList;
	}

	public static List<Integer> listFromId(Integer id) {
		
		if(id == null || id.intValue() == 0) {
			return null;
		}
		
		List<Integer> newList = new ArrayList<>(1);
		newList.add(id);
		return newList;
	}
}
