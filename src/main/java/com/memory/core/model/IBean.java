package com.memory.core.model;

import java.io.Serializable;

public interface IBean<ID extends Serializable> extends Serializable {

	ID getId();
	
	void setId(ID id);
}
