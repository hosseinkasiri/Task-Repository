package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class LogOutDialogFragment extends DialogFragment {

    public static LogOutDialogFragment newInstance() {

        Bundle args = new Bundle();

        LogOutDialogFragment fragment = new LogOutDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Are you sure for Log out ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = LoginPageActivity.newIntent(getActivity());
                        startActivity(intent);
                        getActivity().finish();
                    }
                })
                .setNegativeButton(android.R.string.no,null)
                .create();
    }
}
