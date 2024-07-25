package dev.patika.Library_Management_Systems_RestAPI.Core.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
