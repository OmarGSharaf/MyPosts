package de.pharos.myPosts.exception;

import lombok.extern.java.Log;

@Log
public class PostException extends RuntimeException {

    public PostException(String message) {
        super(message);
        log.severe(message);
    }

    public PostException(String message, Throwable cause) {
        super(message, cause);
        log.severe(message);
    }
}
