package com.sda.testing.exception;

/**
 * If operation executed by the API is incorrect, exception with explanation can be thrown.
 */
public class InvalidOperation extends Exception {
    public InvalidOperation(String message) {
        super(message);
    }
}
