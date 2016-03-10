package com.example.dmitry.bookstore.ui.dashboard;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmitry.bookstore.R;
import com.example.dmitry.bookstore.events.OpenAddAuthorFragmentEvent;
import com.example.dmitry.bookstore.events.OpenAddBookFragmentEvent;
import com.example.dmitry.bookstore.ui.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends BaseFragment {


    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.add_author_button)
    public void onAddAuthorButtonClick() {
        getBus().post(new OpenAddAuthorFragmentEvent());
    }

    @OnClick(R.id.add_book_button)
    public void onAddBookButtonClick() {
        getBus().post(new OpenAddBookFragmentEvent());
    }
}
