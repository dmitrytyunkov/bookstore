package com.example.dmitry.bookstore.error_code;

/**
 * Created by dmitry on 11.03.16.
 */
public enum BookErrorCode {
    TITLE_INCORRECT("Title incorrect"),
    YEARS_INCORRECT("Year of publisher incorrect"),
    PAGES_INCORRECT("Number of pages incorrect");

    private final String errorString;

    BookErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
