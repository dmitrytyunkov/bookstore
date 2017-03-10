package com.example.dmitry.bookstore.error_code;

/**
 * Created by dmitry on 11.03.16.
 */
public enum AuthorErrorCode {
    FIRST_NAME_INCORRECT("First name incorrect"),
    LAST_NAME_INCORRECT("Last name incorrect"),
    BIRTHDAY_INCORRECT("Birthday incorrect"),
    EMAIL_EMPTY("Email empty"),
    EMAIL_INCORRECT("Email incorrect");

    private final String errorString;

    AuthorErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
