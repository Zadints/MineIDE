package org.example.mineide.core.domain.exceptions;

public class ApiException extends RuntimeException {
    public ApiException(String message, Exception e) {
        super(message, e);
    }

}
