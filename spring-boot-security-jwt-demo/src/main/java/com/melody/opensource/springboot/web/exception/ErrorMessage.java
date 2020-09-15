package com.melody.opensource.springboot.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zqhuangc
 */
@Data
@AllArgsConstructor
public class ErrorMessage {

    private int code;

    private String message;

    private Map<String, Object> details;

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
        details = new HashMap<>();
    }

    public <T> ErrorMessage withDetails(String key, T value) {
        details.put(key, value);
        return this;
    }
}
