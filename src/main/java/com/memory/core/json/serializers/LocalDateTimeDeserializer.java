package com.memory.core.json.serializers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	private static final String DATE_TIME_PATTERN = "dd/MM/yyyy";

	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
		LocalDate localDate = LocalDate.parse(jsonParser.getText(), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
		return localDate.atStartOfDay();
	}

}
