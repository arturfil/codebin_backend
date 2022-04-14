package com.arturofilio.codebin.exceptions;

public class EmailExistsException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public EmailExistsException(String message) {
        super(message);
    }
}
