package com.example.task.helper;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.UUID;

public class ConverterId implements PropertyConverter<UUID, String> {

    @Override
    public UUID convertToEntityProperty(String databaseValue) {
        return UUID.fromString(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(UUID entityProperty) {
        return entityProperty.toString();
    }
}