package com.example.dmitry.bookstore.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dmitry.bookstore.R;
import com.example.dmitry.bookstore.model.Author;
import com.example.dmitry.bookstore.model.Book;
import com.example.dmitry.bookstore.model.EAddress;
import com.example.dmitry.bookstore.ui.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowAuthorFragment extends BaseFragment {


    @Bind(R.id.linear_show_author)
    LinearLayout linearShowAuthor;
    @Bind(R.id.show_button)
    Button showButton;

    TextView textView;


    public ShowAuthorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_author, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.show_button)
    public void onShowButtonClick() {
        createAuthorList();
        showButton.setVisibility(View.INVISIBLE);
    }

    private void createAuthorList() {
        List<Author> authors = Author.listAll(Author.class);
        for (Author author : authors) {
            textView = new TextView(getActivity());
            textView.setText(String.format("%s: %s", getString(R.string.first_name_text),
                    author.getFirstName()));
            linearShowAuthor.addView(textView);
            textView = new TextView(getActivity());
            textView.setText(String.format("%s: %s", getString(R.string.last_name_text),
                    author.getLastName()));
            linearShowAuthor.addView(textView);
            textView = new TextView(getActivity());
            textView.setText(String.format("%s: %s", getString(R.string.patronymic_text),
                    author.getPatronymic()));
            linearShowAuthor.addView(textView);
            textView = new TextView(getActivity());
            textView.setText(String.format("%s: %s", getString(R.string.birthday_text),
                    author.getBirthday()));
            linearShowAuthor.addView(textView);
            for (EAddress emails : author.getEmails()) {
                textView = new TextView(getActivity());
                textView.setText(String.format("%s: %s", getString(R.string.email_text),
                        emails.getEmail()));
                linearShowAuthor.addView(textView);
            }
            for (Book book : author.getBooks()) {
                textView = new TextView(getActivity());
                textView.setText(String.format("%s: %s", getString(R.string.book_text),
                        book.toString()));
                linearShowAuthor.addView(textView);
            }
            textView = new TextView(getActivity());
            linearShowAuthor.addView(textView);
        }
    }
}
