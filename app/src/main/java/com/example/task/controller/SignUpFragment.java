package com.example.task.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.task.R;
import com.example.task.model.User;
import com.example.task.model.UserLab;

import java.util.UUID;

public class SignUpFragment extends Fragment {

    private static final String USER_ID = "com.example.task.controller_userId";
    private EditText mFirstName,mLastName,mUsername,mPassword;
    private Button mDoneButton,mCancelButton;
    private UUID mUserId;

    public static SignUpFragment newInstance(UUID userId) {

        Bundle args = new Bundle();
        args.putSerializable(USER_ID,userId);
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up,container,false);
        findViews(view);
        mUserId = (UUID) getArguments().getSerializable(USER_ID);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLab.getInstance(getActivity()).deleteUser(mUserId);
                User user = new User(mUserId);
                user.setFirstName(mFirstName.getText().toString());
                user.setLastName(mLastName.getText().toString());
                user.setUsername(mUsername.getText().toString());
                user.setPassword(mPassword.getText().toString());

                UserLab.getInstance(getActivity()).addUser(user);
                getActivity().finish();
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

    private void findViews(View view) {
        mFirstName = view.findViewById(R.id.first_name_dialog);
        mLastName = view.findViewById(R.id.last_name_dialog);
        mUsername = view.findViewById(R.id.username_dialog);
        mPassword = view.findViewById(R.id.password_dialog);
        mDoneButton = view.findViewById(R.id.done_sign_up);
        mCancelButton = view.findViewById(R.id.cancel_sign_up);
    }
}