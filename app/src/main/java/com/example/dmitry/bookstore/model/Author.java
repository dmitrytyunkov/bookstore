package com.example.dmitry.bookstore.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dmitry on 10.03.16.
 */
public class Author extends SugarRecord {

    String firstName;
    String lastName;
    String patronymic;
    String birthday;
    @Ignore
    List<EAddress> emails;
    @Ignore
    List<Book> books;

    public Author(String firstName, String lastName, String patronymic, String birthday, List<EAddress> emails, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.emails = emails;
        this.books = books;
    }

    public Author(String firstName, String lastName, String patronymic, String birthday) {
        this(firstName, lastName, patronymic, birthday, new ArrayList<EAddress>(), new ArrayList<Book>());
    }

    public Author(String firstName, String lastName, String patronymic, String birthday, List<EAddress> emails) {
        this(firstName, lastName, patronymic, birthday, emails, new ArrayList<Book>());
    }

    public Author() {

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getBirthday() {
        return birthday;
    }

    public List<EAddress> getEmails() {
        return EAddress.find(EAddress.class, "author = ?", getId().toString());
    }

    public List<Book> getBooks() {
        List<AuthorBooks> authorBooksList = AuthorBooks.find(AuthorBooks.class, "author = ?", getId().toString());
        List<Book> books = new ArrayList<>();
        for (AuthorBooks authorBooks : authorBooksList) {
            books.add(Book.findById(Book.class, authorBooks.getBook().getId()));
        }
        return books;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + patronymic + ", " + birthday + " г.р.";
    }
}
