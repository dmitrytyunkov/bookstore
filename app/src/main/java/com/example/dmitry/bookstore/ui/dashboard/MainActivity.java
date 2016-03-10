package com.example.dmitry.bookstore.ui.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dmitry.bookstore.R;
import com.example.dmitry.bookstore.events.OpenAddAuthorFragmentEvent;
import com.example.dmitry.bookstore.events.OpenAddBookFragmentEvent;
import com.example.dmitry.bookstore.ui.AddAuthorFragment;
import com.example.dmitry.bookstore.ui.AddBookFragment;
import com.example.dmitry.bookstore.ui.base.BaseActivity;
import com.squareup.otto.Subscribe;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
            getFragmentManager().beginTransaction().add(R.id.container, new MainMenuFragment()).commit();
    }


    @Subscribe
    public void onOpenAddAuthorFragmentEvent(OpenAddAuthorFragmentEvent event) {
        replaceFragment(new AddAuthorFragment());
    }

    @Subscribe
    public void onOpenAddBookFragmentEvent(OpenAddBookFragmentEvent event) {
        replaceFragment(new AddBookFragment());
    }
}
