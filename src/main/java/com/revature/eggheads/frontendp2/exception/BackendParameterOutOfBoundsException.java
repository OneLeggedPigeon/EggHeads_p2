package com.revature.eggheads.frontendp2.exception;

/**
 * Something to be thrown if the Backend returns a value that doesn't match with Frontend boundary expectations
 */
public class BackendParameterOutOfBoundsException extends Exception{
    public BackendParameterOutOfBoundsException(String message) {
        super(message);
    }
}
