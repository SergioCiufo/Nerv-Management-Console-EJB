package com.company.nervmanagementconsoleejb.domain.utils;

import javax.ejb.Stateless;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
//da controllare l'injezione
@Stateless
@Converter
public class BooleanToCharConverterUtils implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute != null && attribute ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equalsIgnoreCase(dbData);
    }
}