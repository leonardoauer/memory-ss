package com.memory.app.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.memory.app.model.MemoryUI;

@RestController
public class MemoryController {

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/memories")
	public List<MemoryUI> getBinRangeJSON() {

		List<MemoryUI> memories = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			memories.add(new MemoryUI(counter.incrementAndGet(), "This is memory number: " + (i + 1)));
		}

		return memories;
	}

	@RequestMapping(value = "/memory", method = RequestMethod.POST)
	public MemoryUI getBinRangeByJsonPost(@RequestBody String json, @RequestHeader HttpHeaders headers) {
		return new MemoryUI(counter.incrementAndGet(), "XX123456");
	}
}
