package ru.yakovburtsev.socialnetwork.webclient.util.exception;

/**
 * This exception will be thrown if User is not found
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
