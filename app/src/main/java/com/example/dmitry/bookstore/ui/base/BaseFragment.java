package com.example.dmitry.bookstore.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.View;

import com.squareup.otto.Bus;

import butterknife.ButterKnife;

/**
 * Created by dmitry on 10.03.16.
 */
public class BaseFragment extends Fragment {


    private Bus bus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        bus = ((BaseActivity) getActivity()).getBus();
        bus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public Bus getBus() {
        return bus;
    }
}
