package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.task.model.TaskLab;

public class TrashDialogFragment extends DialogFragment {

    private  DialogInterface.OnClickListener listener;

    public TrashDialogFragment(DialogInterface.OnClickListener listener) {
        this.listener = listener;

    }

    public static TrashDialogFragment newInstance(DialogInterface.OnClickListener listener) {
        Bundle args = new Bundle();

        TrashDialogFragment fragment = new TrashDialogFragment(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
         super.onCreateDialog(savedInstanceState);
         return new AlertDialog.Builder(getActivity())
                 .setTitle("are you sure for delete all tasks?")
                 .setPositiveButton(android.R.string.yes, listener)
                 .setNegativeButton(android.R.string.no,null)
                 .create();
    }
}
