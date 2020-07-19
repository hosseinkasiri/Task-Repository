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

public class LogOutGuestDialogFragment extends DialogFragment {

    private static final String USER_ID = "com.example.task.controller_userId";
    private Long mUserId;

    public static LogOutGuestDialogFragment newInstance(Long userId) {
        Bundle args = new Bundle();
        args.putLong(USER_ID,userId);
        LogOutGuestDialogFragment fragment = new LogOutGuestDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.log_out_layout,null);
        mUserId = (Long) getArguments().getSerializable(USER_ID);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskLab.getInstance().clearTasks(mUserId);
                        UserLab.getInstance().deleteUser(mUserId);
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
