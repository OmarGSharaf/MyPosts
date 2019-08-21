package de.pharos.myPosts.exception;

import lombok.extern.java.Log;

@Log
public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
        log.severe(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
        log.severe(message);
    }
}