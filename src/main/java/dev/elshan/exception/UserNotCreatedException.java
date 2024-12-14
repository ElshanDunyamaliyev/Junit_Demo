package dev.elshan.exception;

public class UserNotCreatedException extends RuntimeException {
    public UserNotCreatedException(String message) {
        super(message);
    }
}
