package ru.kuzdikenov.exceptions;

public class UserAlreadyExistsInDatabase extends Exception {
    public UserAlreadyExistsInDatabase(String message) {
        super(message);
    }
    public UserAlreadyExistsInDatabase() {
        super();
    }
}
