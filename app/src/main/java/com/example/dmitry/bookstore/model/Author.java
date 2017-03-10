package com.example.dmitry.bookstore.model;

import com.example.dmitry.bookstore.error_code.AuthorErrorCode;
import com.example.dmitry.bookstore.exception.AuthorException;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by dmitry on 10.03.16.
 */
public class Author extends SugarRecord {

    @Ignore
    final DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
    String firstName;
    String lastName;
    String patronymic;
    Date birthday;
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

    public void setFirstName(String firstName) throws AuthorException {
        if (firstName == null || firstName.length() == 0)
            throw new AuthorException(AuthorErrorCode.FIRST_NAME_INCORRECT.getErrorString());
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws AuthorException {
        if (lastName == null || lastName.length() == 0)
            throw new AuthorException(AuthorErrorCode.LAST_NAME_INCORRECT.getErrorString());
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) throws AuthorException {
        this.birthday = dateFormatter(birthday);
    }

    public DateFormat getFormat() {
        return format;
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
        return firstName + " " + patronymic + " " + lastName + ", " + format.format(birthday) + " г.р.";
    }


    private Date dateFormatter(String dateString) throws AuthorException {
        String[] strings = dateString.split("\\.");
        int[] month30 = new int[]{4, 6, 9, 11};
        int year;
        try {
            year = Integer.parseInt(strings[2]);
        } catch (IndexOutOfBoundsException ex) {
            throw new AuthorException(AuthorErrorCode.BIRTHDAY_INCORRECT.getErrorString());
        }
        int month = Integer.parseInt(strings[1]);
        int day = Integer.parseInt(strings[0]);
        if (year < 1850)
            throw new AuthorException(AuthorErrorCode.BIRTHDAY_INCORRECT.getErrorString());
        if (month > 12)
            throw new AuthorException(AuthorErrorCode.BIRTHDAY_INCORRECT.getErrorString());
        if (day > 31 || (day > 30 && Arrays.binarySearch(month30, month) > -1)
                || (day > 29 && month == 2 && year % 4 == 0) || (day > 28 && month == 2 && year % 4 != 0))
            throw new AuthorException(AuthorErrorCode.BIRTHDAY_INCORRECT.getErrorString());
        Date date;
        try {
            date = format.parse(dateString);
        } catch (ParseException ex) {
            throw new AuthorException(AuthorErrorCode.BIRTHDAY_INCORRECT.getErrorString());
        }
        return date;
    }
}
