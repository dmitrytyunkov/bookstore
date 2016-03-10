package com.example.dmitry.bookstore.model;

import com.orm.SugarRecord;

/**
 * Created by dmitry on 10.03.16.
 */
public class AuthorBooks extends SugarRecord {

    Author author;
    Book book;

    public AuthorBooks(Author author, Book book) {
        this.author = author;
        this.book = book;
    }
}
