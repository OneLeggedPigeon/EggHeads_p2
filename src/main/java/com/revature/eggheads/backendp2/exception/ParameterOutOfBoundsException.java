package com.revature.eggheads.backendp2.exception;

/**
 * Something to be thrown if the Backend returns a value that doesn't match with Frontend boundary expectations
 */
public class ParameterOutOfBoundsException extends Exception{
    public ParameterOutOfBoundsException(String message) {
        super(message);
    }
}
