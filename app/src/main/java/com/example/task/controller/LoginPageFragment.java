package com.example.task.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.helper.Toaster;
import com.example.task.model.User;
import com.example.task.model.UserLab;

import java.util.Objects;
import java.util.UUID;

public class LoginPageFragment extends Fragment {
    private EditText mUsername,mPassword;
    private Button mLoginButton;
    private TextView mSignUp,mGuest;
    private User mUser;

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
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setGuest(false);
                UserLab.getInstance().addUser(user);
                Intent intent = SignUpActivity.newIntent(getActivity(),user.getUuid());
                startActivity(intent);
            }
        });
        mGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setGuest(true);
                UserLab.getInstance().addUser(user);
                Intent intent = MainActivity.newIntent(getActivity(),user.getUuid());
                startActivity(intent);
                getActivity().finish();
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mUser = UserLab.getInstance().getUser(mUsername.getText().toString(),mPassword.getText().toString());
               if (mUser != null){
                   Intent intent = MainActivity.newIntent(getActivity(),mUser.getUuid());
                   startActivity(intent);
                   getActivity().finish();
               }
               else {
                   Toaster.makeToast(getActivity(),"please check username or password ");
               }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity)
                Objects.requireNonNull(getActivity())).
                getSupportActionBar()).hide();
    }

    private void findViews(View view) {
        mUsername = view.findViewById(R.id.username_edit_text);
        mPassword = view.findViewById(R.id.password_edit_text);
        mLoginButton = view.findViewById(R.id.login_button);
        mSignUp = view.findViewById(R.id.sign_up_text);
        mGuest = view.findViewById(R.id.guest_login);
    }
}