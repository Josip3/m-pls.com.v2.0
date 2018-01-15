package com.mpls.v2.utils.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateDeserializer extends JsonDeserializer<LocalDateTime> {

    private static DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ISO_DATE_TIME;

    public static LocalDateTime toLocalDateParse(String date) {
        if (date == null || date.isEmpty()) {
            return LocalDateTime.now();
        }
        try {
            return LocalDateTime.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        try {
            String date = jsonParser.getText();
            return LocalDateTime.parse(date, dateTimeFormatter);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (Exception e) {
            return null;
        }
    }
}
