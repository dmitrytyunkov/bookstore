package com.example.dmitry.bookstore.application;

import com.orm.SugarApp;
import com.squareup.otto.Bus;

/**
 * Created by dmitry on 10.03.16.
 */
public class App extends SugarApp {

    private Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new Bus();
    }


    public Bus getBus() {
        return bus;
    }
}
