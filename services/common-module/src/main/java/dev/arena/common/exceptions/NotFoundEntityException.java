package dev.arena.common.exceptions;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message) {
        super(message);
    }
}
