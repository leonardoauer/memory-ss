package com.memory.core.converters;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Classe para converter entre LocalDateTime para Date (sql.Date)
 */
@Converter(autoApply=true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime,Date>{

	@Override
	public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
		return (localDateTime == null ? null : Date.valueOf(localDateTime.toLocalDate()));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Date sqlDate) {
		
		if(sqlDate==null) {
			return null;
		}
		
		Timestamp timeStamp = new Timestamp(sqlDate.getTime());
		return (sqlDate == null ? null : timeStamp.toLocalDateTime());
	}

}
