package ru.yakovburtsev.socialnetwork.core.util.exception;

/**
 * Instances of this exception class occur when user registration fail.
 */
public class RegistrationException extends RuntimeException {
    public RegistrationException(String message) {
        super(message);
    }
}
