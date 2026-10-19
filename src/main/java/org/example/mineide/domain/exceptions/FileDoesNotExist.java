package org.example.mineide.domain.exceptions;

public class FileDoesNotExist extends RuntimeException {
    public FileDoesNotExist(String message) {
        super(message);
    }
}
