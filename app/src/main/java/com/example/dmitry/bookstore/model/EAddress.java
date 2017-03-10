package com.example.dmitry.bookstore.model;

import android.util.Patterns;

import com.example.dmitry.bookstore.error_code.AuthorErrorCode;
import com.example.dmitry.bookstore.exception.AuthorException;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by dmitry on 10.03.16.
 */
public class EAddress extends SugarRecord {

    @Unique
    String email;
    Author author;

    public EAddress(String email, Author author) throws AuthorException {
        setEmail(email);
        this.author = author;
    }

    public EAddress() {

    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) throws AuthorException {
        if (email == null || email.length() == 0)
            throw new AuthorException(AuthorErrorCode.EMAIL_EMPTY.getErrorString());
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            throw new AuthorException(AuthorErrorCode.EMAIL_INCORRECT.getErrorString());
        this.email = email;
    }
}
