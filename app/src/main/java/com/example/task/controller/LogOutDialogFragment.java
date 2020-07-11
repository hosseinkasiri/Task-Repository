package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.task.R;
import com.example.task.model.TaskLab;
import com.example.task.model.User;
import com.example.task.model.UserLab;

import java.util.UUID;

public class LogOutDialogFragment extends DialogFragment {

    private static final String USER_ID = "com.example.task.controller_userId";
    private UUID mUserId;
    private User mUser;

    public static LogOutDialogFragment newInstance(UUID userId) {
        Bundle args = new Bundle();
        args.putSerializable(USER_ID,userId);
        LogOutDialogFragment fragment = new LogOutDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.log_out_layout,null);
        mUserId = (UUID) getArguments().getSerializable(USER_ID);
        mUser = UserLab.getInstance(getActivity()).getUserById(mUserId);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mUser.isGuest()) {
                            TaskLab.getInstance(getActivity()).clearTasks(mUserId);
                            UserLab.getInstance(getActivity()).deleteUser(mUserId);
                        }
                        else {
                            mUser.setFirstActivity(false);
                            UserLab.getInstance(getActivity()).updateUser(mUser);
                        }
                        Intent intent = LoginPageActivity.newIntent(getActivity());
                        startActivity(intent);
                        getActivity().finish();
                    }
                })
                .setNeutralButton("sign up", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = SignUpActivity.newIntent(getActivity(),mUserId);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.cancel,null)
                .create();
    }
}
