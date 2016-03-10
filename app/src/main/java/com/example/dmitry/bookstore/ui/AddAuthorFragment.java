package com.example.dmitry.bookstore.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dmitry.bookstore.R;
import com.example.dmitry.bookstore.model.Author;
import com.example.dmitry.bookstore.model.EAddress;
import com.example.dmitry.bookstore.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAuthorFragment extends BaseFragment {


    @Bind(R.id.first_name_edit_text)
    EditText firstNameEditText;
    @Bind(R.id.last_name_edit_text)
    EditText lastNameEditText;
    @Bind(R.id.patronymic_edit_text)
    EditText patronymicEditText;
    @Bind(R.id.birthday_edit_text)
    EditText birthdayEditText;
    @Bind(R.id.email_edit_text)
    EditText emailEditText;
    @Bind(R.id.linear_main)
    LinearLayout linearMain;

    List<EditText> editTexts = new ArrayList<>();
    @Bind(R.id.add_email_button)
    Button addEmailButton;

    public AddAuthorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_author, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.add_email_button)
    public void onAddEmailButtonClick() {
        EditText editText = new EditText(getActivity());
        editText.setHint(R.string.email_text);
        linearMain.addView(editText);
        editTexts.add(editText);
    }

    @OnClick(R.id.submit_author_button)
    public void onSubmitAuthorButtonClick() {
        Author author = new Author(firstNameEditText.getText().toString(),
                lastNameEditText.getText().toString(), patronymicEditText.getText().toString(),
                birthdayEditText.getText().toString());
        author.save();
        EAddress eAddress = new EAddress(emailEditText.getText().toString(), author);
        eAddress.save();
        for (EditText editText : editTexts) {
            eAddress = new EAddress(editText.getText().toString(), author);
            eAddress.save();
        }

        List<Author> authors = author.listAll(Author.class);
        List<EAddress> eAddressList = eAddress.listAll(EAddress.class);
    }
}
