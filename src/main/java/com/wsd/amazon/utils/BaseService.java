package com.wsd.amazon.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    protected AmazonServiceLogger logger;
    public ObjectMapper objectMapper;

    @Autowired
    public void setLogger(AmazonServiceLogger logger) {
        this.logger = logger;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
