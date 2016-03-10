package com.example.dmitry.bookstore.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 10.03.16.
 */
public class Book extends SugarRecord {

    String title;
    int year;
    int pages;
    @Ignore
    List<Author> authors;

    public Book(String title, int year, int pages, List<Author> authors) {
        this.title = title;
        this.year = year;
        this.pages = pages;
        this.authors = authors;
    }

    public Book(String title, int year, int pages) {
        this(title, year, pages, new ArrayList<Author>());
    }

    public Book() {

    }
}
