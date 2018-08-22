package com.memory.app.model.ui;

import java.io.File;
import java.net.URI;

public class MemoryContentUI {

	private String text;
	private File file;
	private URI uri;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}
}
