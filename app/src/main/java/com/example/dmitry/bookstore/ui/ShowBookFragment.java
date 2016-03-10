package com.example.dmitry.bookstore.ui;


import android.app.Fragment;
import android.os.Bundle;
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
public class ShowBookFragment extends BaseFragment {


    @Bind(R.id.linear_show_books)
    LinearLayout linearShowBooks;
    @Bind(R.id.show_button)
    Button showButton;

    TextView textView;


    public ShowBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_book, container, false);
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
        List<Book> books = Author.listAll(Book.class);
        for (Book book : books) {
            textView = new TextView(getActivity());
            textView.setText(String.format("%s: %s", getString(R.string.title_book_text),
                    book.getTitle()));
            linearShowBooks.addView(textView);
            textView = new TextView(getActivity());
            textView.setText(String.format("%s: %s", getString(R.string.year_text),
                    book.getYear()));
            linearShowBooks.addView(textView);
            textView = new TextView(getActivity());
            textView.setText(String.format("%s: %s", getString(R.string.pages_text),
                    book.getPages()));
            linearShowBooks.addView(textView);
            for (Author author : book.getAuthors()) {
                textView = new TextView(getActivity());
                textView.setText(String.format("%s: %s", getString(R.string.author_text),
                        author.toString()));
                linearShowBooks.addView(textView);
            }
            textView = new TextView(getActivity());
            linearShowBooks.addView(textView);
        }
    }
}
