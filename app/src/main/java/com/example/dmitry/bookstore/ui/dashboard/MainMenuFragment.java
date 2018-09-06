package com.example.dmitry.bookstore.ui.dashboard;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmitry.bookstore.R;
import com.example.dmitry.bookstore.events.OpenAddAuthorFragmentEvent;
import com.example.dmitry.bookstore.events.OpenAddBookFragmentEvent;
import com.example.dmitry.bookstore.events.OpenShowAuthorFragmentEvent;
import com.example.dmitry.bookstore.events.OpenShowBookFragmentEvent;
import com.example.dmitry.bookstore.ui.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends BaseFragment {

    Unbinder unbinder;

    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.add_author_button)
    public void onAddAuthorButtonClick() {
        getBus().post(new OpenAddAuthorFragmentEvent());
    }

    @OnClick(R.id.add_book_button)
    public void onAddBookButtonClick() {
        getBus().post(new OpenAddBookFragmentEvent());
    }

    @OnClick(R.id.show_author_button)
    public void onShowAuthorButtonClick() {
        getBus().post(new OpenShowAuthorFragmentEvent());
    }

    @OnClick(R.id.show_book_button)
    public void onShowBookButtonClick() {
        getBus().post(new OpenShowBookFragmentEvent());
    }
}
