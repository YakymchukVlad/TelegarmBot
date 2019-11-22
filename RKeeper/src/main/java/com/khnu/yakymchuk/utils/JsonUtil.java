package com.khnu.yakymchuk.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class JsonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void saveInformation(File file, Object object) {
        try {
            objectMapper.writeValue(file, object);
        } catch (IOException e) {
            LOG.error("Cannot save dish to JSON file : " + e.getMessage());
        }
    }

    public static <T> T getInformation(TypeReference<T> typeReference, File file) {
        try {
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            LOG.error("Cannot get dish from JSON file : " + e.getMessage());
        }
        return null;
    }

}
