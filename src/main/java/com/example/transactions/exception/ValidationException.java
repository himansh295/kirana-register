package com.example.transactions.exception;

import lombok.Getter;

@Getter
public class ValidationException extends Exception{
    private String errorCode;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
