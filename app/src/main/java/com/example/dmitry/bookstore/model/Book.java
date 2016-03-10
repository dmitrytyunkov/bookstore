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

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public List<Author> getAuthors() {
        AuthorBooks aB = AuthorBooks.first(AuthorBooks.class);
        aB = AuthorBooks.findById(AuthorBooks.class, 1);
        List<AuthorBooks> authorBooksList = AuthorBooks.find(AuthorBooks.class, "book = ?", getId().toString());
        List<Author> authors = new ArrayList<>();
        for (AuthorBooks authorBooks : authorBooksList) {
            authors.add(Author.findById(Author.class, authorBooks.getAuthor().getId()));
        }
        return authors;
    }

    @Override
    public String toString() {
        return  title + ", " + year + "г., " + pages + "с.";
    }
}
