package ru.yakovburtsev.socialnetwork.core.util.exception;

/**
 * This class objects describe exception when entity not found.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
