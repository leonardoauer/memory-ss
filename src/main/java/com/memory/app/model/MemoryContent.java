package com.memory.app.model;

import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.memory.core.model.Bean;

@Entity
public class MemoryContent extends Bean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@MapsId
	private Memory memory;
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
