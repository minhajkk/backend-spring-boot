package com.bsf.backend.exception;

public class UserExistException extends Exception {
    public UserExistException(String msg) {
        super(msg);
    }
}
