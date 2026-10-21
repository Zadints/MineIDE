package org.example.mineide.domain.exceptions;

public class AlreadySetValueEntityException extends RuntimeException {
    public AlreadySetValueEntityException(String message) {
        super(message);
    }
}
