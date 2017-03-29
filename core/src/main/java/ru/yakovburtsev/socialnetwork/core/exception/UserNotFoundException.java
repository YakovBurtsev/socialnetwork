package ru.yakovburtsev.socialnetwork.core.exception;

/**
 * This exception will be thrown if User is not found
 */
public class UserNotFoundException extends RuntimeException {
    private static final long SerialVersionUID = 1;

    public UserNotFoundException(String message) {
        super(message);
    }
}
