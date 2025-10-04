package ru.kuzdikenov.http;

public enum HttpMethods {
    GET ("GET"),
    POST ("POST"),
    PUT ("PUT"),
    DELETE ("DELETE");

    private String value;

    HttpMethods(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
