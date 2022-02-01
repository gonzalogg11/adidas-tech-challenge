package com.adidas.oracle.util;

import com.adidas.oracle.dto.ApiResponseDTO;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class JSONUtil {

    private static final Logger log = LoggerFactory.getLogger(JSONUtil.class);

    private JSONUtil() {
    }

    public static <T> T loadJson(final String json, final Class<T> clazz) {
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readerFor(clazz).readValue(json);
        } catch (Exception e) {
            log.error(e.getMessage());
            return (T) new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
