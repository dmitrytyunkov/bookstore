package com.example.dmitry.bookstore.ui;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dmitry.bookstore.R;
import com.example.dmitry.bookstore.model.Author;
import com.example.dmitry.bookstore.model.AuthorBooks;
import com.example.dmitry.bookstore.model.Book;
import com.example.dmitry.bookstore.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookFragment extends BaseFragment {


    @Bind(R.id.title_book_edit_text)
    EditText titleBookEditText;
    @Bind(R.id.year_edit_text)
    EditText yearEditText;
    @Bind(R.id.pages_edit_text)
    EditText pagesEditText;
    @Bind(R.id.layout_add_book)
    LinearLayout layoutAddBook;
    @Bind(R.id.author_list_view)
    ListView authorListView;
    @Bind(R.id.author_button)
    Button authorButton;

    List<Author> authors;
    List<Author> authorList;


    public AddBookFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.author_button)
    public void onAuthorButtonClick() {
        createListAuthor();
        authorButton.setVisibility(View.INVISIBLE);
    }


    private void createListAuthor() {
        authors = Author.listAll(Author.class);
        ArrayAdapter<Author> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.select_dialog_multichoice, authors);
        authorListView.setAdapter(adapter);
    }


    @OnItemClick(R.id.author_list_view)
    public void onAuthorListViewItemClick(AdapterView<?> parent) {
        authorList = new ArrayList<>();
        SparseBooleanArray chosen = ((ListView) parent).getCheckedItemPositions();
        for (int i = 0; i < chosen.size(); i++) {
            if (chosen.valueAt(i)) {
                authorList.add(authors.get(chosen.keyAt(i)));
            }
        }
    }


    @OnClick(R.id.submit_book_button)
    public void onSubmitBookButton() {
        Book book = new Book(titleBookEditText.getText().toString(),
                Integer.parseInt(yearEditText.getText().toString()),
                Integer.parseInt(pagesEditText.getText().toString()));
        book.save();
        for (Author author : authorList) {
            AuthorBooks authorBooks = new AuthorBooks(author, book);
            authorBooks.save();
        }
    }
}
