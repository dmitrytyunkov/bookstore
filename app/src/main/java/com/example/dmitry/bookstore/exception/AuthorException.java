package com.example.dmitry.bookstore.exception;

/**
 * Created by dmitry on 11.03.16.
 */
public class AuthorException extends Exception {

    public AuthorException() {
    }

    public AuthorException(String detailMessage) {
        super(detailMessage);
    }

    public AuthorException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AuthorException(Throwable throwable) {
        super(throwable);
    }
}
