package com.example.dmitry.bookstore.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

/**
 * Created by dmitry on 10.03.16.
 */
public class EAddress extends SugarRecord {

    @Unique
    String email;
    Author author;

    public EAddress(String email, Author author) {
        this.email = email;
        this.author = author;
    }

    public EAddress() {

    }

    public String getEmail() {
        return email;
    }
}
