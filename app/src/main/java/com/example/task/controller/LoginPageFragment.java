package com.example.task.controller;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.task.R;

public class LoginPageFragment extends Fragment {
    private EditText mUsername,mPassword;
    private Button mLoginButton;
    private TextView mSignUp;

    public static LoginPageFragment newInstance() {

        Bundle args = new Bundle();

        LoginPageFragment fragment = new LoginPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {
        mUsername = view.findViewById(R.id.username_edit_text);
        mPassword = view.findViewById(R.id.password_edit_text);
        mLoginButton = view.findViewById(R.id.login_button);
        mSignUp = view.findViewById(R.id.sign_up_text);
    }
}