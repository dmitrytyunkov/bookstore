package com.example.dmitry.bookstore.exception;

/**
 * Created by dmitry on 11.03.16.
 */
public class BookException extends Exception {
    public BookException() {
    }

    public BookException(String detailMessage) {
        super(detailMessage);
    }

    public BookException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BookException(Throwable throwable) {
        super(throwable);
    }
}
