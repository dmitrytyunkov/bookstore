package com.example.dmitry.bookstore.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmitry.bookstore.R;
import com.example.dmitry.bookstore.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowAuthorFragment extends BaseFragment {


    public ShowAuthorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_author, container, false);
    }


}
