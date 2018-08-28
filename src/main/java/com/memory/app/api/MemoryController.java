package com.memory.app.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.memory.app.model.Memory;
import com.memory.app.model.MemoryContent;
import com.memory.app.model.Tag;
import com.memory.app.model.ui.MemoryUI;
import com.memory.app.service.MemoryService;
import com.memory.app.service.TagService;
import com.memory.core.util.BeanUIUtils;

@RestController
public class MemoryController {

	@Autowired
	private MemoryService memoryService;
	
	@Autowired
	private TagService tagService;

	@RequestMapping(value = "/memories")
	public List<MemoryUI> listMemories() {
		List<Memory> memories = memoryService.findAll();
		return MemoryUI.modeltoUI(memories);
	}
	
	@RequestMapping(value = "/memories/{id}")
	public MemoryUI getMemory(@PathVariable Long id) {
		Memory memory = memoryService.findOne(id);
		return MemoryUI.modeltoUI(memory); 
	}
	
	@RequestMapping(value = "/memories", method = RequestMethod.PUT)
	public void updateMemory(@RequestBody MemoryUI memoryUI) {

		// Fetch the memory from DB
		Memory memory = memoryService.findOne(memoryUI.getId());
		
		// Copy UI values to model
		memory.setTitle(memoryUI.getTitle());
		memory.getContent().setText(memoryUI.getContent().getText());
		
		// Add the tags to the memory
		List<Tag> tags = tagService.findAll(BeanUIUtils.getIds(memoryUI.getTags()));
		memory.setTags(tags);
		
		// Update the model
		memoryService.save(memory);
	}

	@RequestMapping(value = "/memories", method = RequestMethod.POST)
	public void saveMemory(@RequestBody MemoryUI memoryUI) {
		
		// Copy UI values to model
		Memory memory = new Memory();
		memory.setTitle(memoryUI.getTitle());

		// Add the tags to the memory
		List<Tag> tags = tagService.findAll(BeanUIUtils.getIds(memoryUI.getTags()));
		memory.setTags(tags);
		
		MemoryContent content = new MemoryContent();
		content.setText(memoryUI.getContent().getText());
		content.setMemory(memory);
		memory.setContent(content);
		
		// Save the model
		memory = memoryService.save(memory);
	}
}
