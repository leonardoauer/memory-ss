package com.memory.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memory.app.model.Memory;
import com.memory.app.repository.MemoryRepository;
import com.memory.core.service.CrudService;

@Service
public class MemoryServiceImpl extends CrudService<Memory> implements MemoryService {

	@Autowired
	private MemoryRepository memoryRepository;

	@Override
	public List<Memory> findAll() {
		return memoryRepository.findAll();
	}

	@Override
	public Memory findOne(Long id) {
		return memoryRepository.findById(id).get();
	}

	@Override
	public Memory save(Memory memory) {
		return memoryRepository.save(memory);
	}
}
