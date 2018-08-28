package com.memory.app.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.memory.core.model.Bean;

@Entity
public class Memory extends Bean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private String title;
	private Boolean active;

	@ManyToMany
	@JoinTable(name = "memory_tag", joinColumns = @JoinColumn(name = "memory_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags;

	@OneToOne(mappedBy = "memory", cascade = CascadeType.ALL)
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

	public MemoryContent getContent() {
		return content;
	}

	public void setContent(MemoryContent content) {
		this.content = content;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = new HashSet<>(tags);
	}
}
