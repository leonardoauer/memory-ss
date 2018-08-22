package com.memory.core.model.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.memory.core.model.Bean;

public abstract class Filter<E extends Bean> {

	private String searchParam = "";
	private List<E> beans;
	private List<String> orderbyFields;
	private ORDER order;
	private int from;
	private int limit;
	
	protected abstract List<String> orderbyFields();
	
	public void orderBy(String orderByField) {
		Assert.notNull(orderbyFields(), "Did you forget to implement orderbyList() method?");
		Assert.isTrue(orderbyFields().contains(orderByField), "Bad parameter. Use values defined in your orderbyList() implementation");
		
		if(this.orderbyFields == null) {
			this.orderbyFields = new ArrayList<>();
		}
		
		this.orderbyFields.add(orderByField);
	}	
	
	public String getOrderByFields() {
		
		if (CollectionUtils.isEmpty(orderbyFields)) {
			return null;
		}

		return StringUtils.join(orderbyFields.toArray(), ",");
	}	

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean hasLimit() {
		return this.limit != 0;
	}

	public void setOrder(ORDER order) {
		this.order = order;
	}

	public boolean isOrderBy() {
		return this.orderbyFields != null;
	}

	public ORDER getOrder() {
		return order;
	}

	public List<E> getBeans() {
		return beans;
	}

	public void setBeans(List<E> beans) {
		this.beans = beans;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}
}
