package com.memory.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;

public class TimeUtil {
	
	private static final String LOCAL_DATE_TIME_FORMATTER = "dd/MM/yyyy HH:mm";
	private static final String HOUR_MIN = "00:00";

	public static LocalDateTime toLocalDateTime(String dateString) {
		
		if(StringUtils.isBlank(dateString)) {
			return LocalDateTime.MIN;
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMATTER);
		
		try {
			return LocalDateTime.parse(dateString + StringUtils.SPACE + HOUR_MIN, dtf);
		} catch(DateTimeParseException e) {
			return LocalDateTime.MIN;
		}
	}
}
