package com.memory.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memory.app.model.Tag;
import com.memory.app.repository.TagRepository;
import com.memory.core.service.CrudService;

@Service
public class TagServiceImpl extends CrudService<Tag> implements TagService {
	
	@Autowired
	private TagRepository tagRepository;

	@Override
	public List<Tag> findAll(List<Long> ids) {
		return tagRepository.findAllById(ids);
	}

}
