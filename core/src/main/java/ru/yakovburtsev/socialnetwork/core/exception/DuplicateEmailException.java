package ru.yakovburtsev.socialnetwork.core.exception;

import java.io.Serializable;

public class DuplicateEmailException extends RuntimeException implements Serializable {
    private static final long SerialVersionUID = 1;

    public DuplicateEmailException(String message) {
        super(message);
    }
}
