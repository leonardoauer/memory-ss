package com.memory.app.model.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.memory.app.model.Memory;
import com.memory.core.util.BeanUtils;

public class MemoryUI extends BeanUI {

	private String tag;
	private String title;
	private MemoryContentUI content;
	
	public MemoryUI() {}

	public MemoryUI(Long id, String title) {
		this.setId(id);
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MemoryContentUI getContent() {
		return content;
	}

	public void setContent(MemoryContentUI content) {
		this.content = content;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public static MemoryUI mapModeltoUI(Memory memory) {
		
		if(BeanUtils.isNull(memory)) {
			return null;
		}
		
		// Create MemoryUI
		MemoryUI memoryUI = new MemoryUI();
		memoryUI.setId(memory.getId());
		memoryUI.setTag(memory.getTag());
		memoryUI.setTitle(memory.getTitle());
		
		// Create MemoryContentUI
		MemoryContentUI contentUI = new MemoryContentUI();
		contentUI.setText(memory.getContent().getText());
		memoryUI.setContent(contentUI);
		
		return memoryUI;
	}
	
	public static List<MemoryUI> mapModeltoUI(List<Memory> memories) {
		
		if(CollectionUtils.isEmpty(memories)) {
			return null;
		}
		
		// Create the memories array list
		List<MemoryUI> memoriesUI = new ArrayList<>(memories.size());
		for (Memory memory : memories) {
			memoriesUI.add(mapModeltoUI(memory));
		}

		return memoriesUI;
	}
}
