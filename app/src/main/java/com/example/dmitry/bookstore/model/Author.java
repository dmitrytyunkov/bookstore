package com.example.dmitry.bookstore.model;

import com.example.dmitry.bookstore.error_code.AuthorErrorCode;
import com.example.dmitry.bookstore.exception.AuthorException;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
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

    public Author(String firstName, String lastName, String patronymic, String birthday, List<EAddress> emails, List<Book> books) throws AuthorException {
        setFirstName(firstName);
        setLastName(lastName);
        this.patronymic = patronymic;
        setBirthday(birthday);
        this.emails = emails;
        this.books = books;
    }

    public Author(String firstName, String lastName, String patronymic, String birthday) throws AuthorException {
        this(firstName, lastName, patronymic, birthday, new ArrayList<EAddress>(), new ArrayList<Book>());
    }

    public Author(String firstName, String lastName, String patronymic, String birthday, List<EAddress> emails) throws AuthorException {
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


    public void setFirstName(String firstName) throws AuthorException {
        if(firstName == null || firstName.length()==0)
            throw new AuthorException(AuthorErrorCode.FIRST_NAME_INCORRECT.getErrorString());
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws AuthorException {
        if(firstName == null || firstName.length()==0)
            throw new AuthorException(AuthorErrorCode.LAST_NAME_INCORRECT.getErrorString());
        this.lastName = lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + patronymic + ", " + birthday + " г.р.";
    }
}
