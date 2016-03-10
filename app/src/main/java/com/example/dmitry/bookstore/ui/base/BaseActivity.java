package com.example.dmitry.bookstore.ui.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitry.bookstore.R;
import com.example.dmitry.bookstore.application.App;
import com.squareup.otto.Bus;

/**
 * Created by dmitry on 10.03.16.
 */
public class BaseActivity extends Activity {


    private Bus bus;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus = ((App) getApplication()).getBus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, true, null);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        replaceFragment(fragment, addToBackStack, null);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, @Nullable String key) {
        FragmentTransaction replaceTransaction = getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment);

        if (addToBackStack)
            replaceTransaction
                    .addToBackStack(key);

        replaceTransaction
                .commit();
    }

    public boolean returnToBackStack(String stackKey, boolean inclusive) {
        return getFragmentManager()
                .popBackStackImmediate(stackKey, inclusive ? FragmentManager.POP_BACK_STACK_INCLUSIVE : 0);
    }

    public Bus getBus() {
        return bus;
    }
}
