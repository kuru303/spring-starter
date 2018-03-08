package br.com.mbamobi.wscapes.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToSimNaoConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean value) {
		if (value == null) {
			return null;
		} else {
			return value ? "S" : "N";
		}
	}

	@Override
	public Boolean convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		} else if (value.equals("S")) {
			return true;
		} else if (value.equals("N")) {
			return false;
		} else {
			throw new IllegalStateException("Invalid boolean character: " + value);
		}
	}
}
