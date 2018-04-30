package com.yuen.springapirelationfix.exceptions;


public class InvalidRequestBodyException extends RuntimeException{

    public InvalidRequestBodyException(String message) {
        super(message);
    }

}
