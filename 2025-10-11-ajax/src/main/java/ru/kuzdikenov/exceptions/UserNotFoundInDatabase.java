package ru.kuzdikenov.exceptions;

public class UserNotFoundInDatabase extends Exception {
    public UserNotFoundInDatabase(String message) {
        super(message);
    }
    public UserNotFoundInDatabase() {super();}
}
