package com.melody.opensource.springboot.web.exception;

/**
 * @author zqhuangc
 */
public class UserNameAlreadyExistException extends  RuntimeException {

    public UserNameAlreadyExistException() {
    }

    public UserNameAlreadyExistException(String message) {
        super(message);
    }

}
