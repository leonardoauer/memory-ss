package com.memory.app.model.ui;

import com.memory.app.model.Tag;

public class TagUI extends BeanUI<Tag> {
	
	private String name;
	private String description;
	
	@Override
	public BeanUI<Tag> mapModeltoUI(Tag tag) {
		TagUI tagUI = new TagUI();
		tagUI.setId(tag.getId());
		tagUI.setName(tag.getName());
		tagUI.setDescription(tag.getDescription());
		return tagUI;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
}
