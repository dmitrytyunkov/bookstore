package com.example.dmitry.bookstore.model;

import com.example.dmitry.bookstore.error_code.BookErrorCode;
import com.example.dmitry.bookstore.exception.BookException;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

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

    public Book(String title, int year, int pages, List<Author> authors) throws BookException {
        setTitle(title);
        setYear(year);
        setPages(pages);
        this.authors = authors;
    }

    public Book(String title, int year, int pages) throws BookException {
        this(title, year, pages, new ArrayList<Author>());
    }

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws BookException {
        if (title == null || title.length() == 0)
            throw new BookException(BookErrorCode.TITLE_INCORRECT.getErrorString());
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws BookException {
        if (year < 1900)
            throw new BookException(BookErrorCode.YEARS_INCORRECT.getErrorString());
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) throws BookException {
        if (pages < 1)
            throw new BookException(BookErrorCode.PAGES_INCORRECT.getErrorString());
        this.pages = pages;
    }

    public List<Author> getAuthors() {
        List<AuthorBooks> authorBooksList = AuthorBooks.find(AuthorBooks.class, "book = ?", getId().toString());
        List<Author> authors = new ArrayList<>();
        for (AuthorBooks authorBooks : authorBooksList) {
            authors.add(Author.findById(Author.class, authorBooks.getAuthor().getId()));
        }
        return authors;
    }

    @Override
    public String toString() {
        return title + ", " + year + "г., " + pages + "с.";
    }
}
