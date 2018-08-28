package com.memory.app.model.ui;

import java.util.Set;

import com.memory.app.model.Memory;

public class MemoryUI extends BeanUI<Memory> {

	private Set<TagUI> tags;
	private String title;
	private MemoryContentUI content;
	
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
	
	public Set<TagUI> getTags() {
		return tags;
	}

	public void setTags(Set<TagUI> tags) {
		this.tags = tags;
	}
	
	@Override
	public BeanUI<Memory> mapModeltoUI(Memory memory) {

		// Create MemoryUI
		MemoryUI memoryUI = new MemoryUI();
		memoryUI.setId(memory.getId());
		memoryUI.setTags(TagUI.modeltoUI(memory.getTags()));
		memoryUI.setTitle(memory.getTitle());
		
		// Create MemoryContentUI
		MemoryContentUI contentUI = new MemoryContentUI();
		contentUI.setText(memory.getContent().getText());
		memoryUI.setContent(contentUI);
		
		return memoryUI;
	}
}
