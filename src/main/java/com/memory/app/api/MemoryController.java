package com.memory.app.api;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.memory.app.model.Memory;
import com.memory.app.model.ui.MemoryUI;
import com.memory.app.service.MemoryService;

@RestController
public class MemoryController {

	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private MemoryService memoryService;

	@RequestMapping(value = "/memories")
	public List<MemoryUI> listMemories() {
		List<Memory> memories = memoryService.findAll();
		return MemoryUI.mapModeltoUI(memories);
	}
	
	@RequestMapping(value = "/memories/{id}")
	public MemoryUI getMemory(@PathVariable Long id) {
		Memory memory = memoryService.findOne(id);
		return MemoryUI.mapModeltoUI(memory); 
	}

	@RequestMapping(value = "/memory", method = RequestMethod.POST)
	public MemoryUI saveMemory(@RequestBody String json, @RequestHeader HttpHeaders headers) {
		return new MemoryUI(counter.incrementAndGet(), "XX123456");
	}
}
