package com.memory.app.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.memory.core.model.Bean;

@Entity
public class Memory extends Bean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private String tag;
	private String title;
	private Boolean active;
	
	@OneToOne(mappedBy = "memory")
	private MemoryContent content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public MemoryContent getContent() {
		return content;
	}

	public void setContent(MemoryContent content) {
		this.content = content;
	}
}
