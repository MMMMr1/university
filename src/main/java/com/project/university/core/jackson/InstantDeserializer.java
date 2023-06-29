//package com.project.university.mapper.jackson;
//
//import com.fasterxml.jackson.core.JacksonException;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//
//import java.io.IOException;
//import java.time.Instant;
//
//public class InstantDeserializer extends JsonDeserializer<Instant> {
//    @Override
//    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
//        String date = jsonParser.getText();
//        if (date.isEmpty()) {
//            return null;
//        }
//        Long decode =Long.decode(date);
//        return Instant.ofEpochMilli(decode);
//    }
//}
