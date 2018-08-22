package com.memory.core.model.filter;

public enum ORDER {

	ASC, DESC;

	public static ORDER getOrderByName(String orderName) {
		if (orderName == null) {
			return null;
		}

		for (ORDER order : ORDER.values()) {
			if (order.name().equals(orderName.toUpperCase())) {
				return order;
			}
		}

		return null;
	}
}
