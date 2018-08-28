package com.memory.core.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.memory.app.model.ui.BeanUI;
import com.memory.core.model.Bean;

public class BeanUIUtils {

	public static <S extends Bean, T extends BeanUI<S>> List<Long> getIds(Collection<T> beansUI) {
		return beansUI.stream().map(BeanUI::getId).collect(Collectors.toList());
	}
}
