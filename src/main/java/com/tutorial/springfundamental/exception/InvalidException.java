package com.tutorial.springfundamental.exception;

public class InvalidException extends RuntimeException {

    public InvalidException(String message) {
        super(message);
    }
}
