package ru.kuzdikenov.exceptions;

public class UserNotFoundInDatabase extends RuntimeException {
  public UserNotFoundInDatabase(String message) {
    super(message);
  }
}
