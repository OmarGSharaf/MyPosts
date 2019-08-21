package de.pharos.myPosts.exception;

import lombok.extern.java.Log;

@Log
public class JwtException extends RuntimeException {

    public JwtException(String message) {
        super(message);
        log.severe(message);
    }

    public JwtException(String message, Throwable cause) {
        super(message, cause);
        log.severe(message);
    }
}
