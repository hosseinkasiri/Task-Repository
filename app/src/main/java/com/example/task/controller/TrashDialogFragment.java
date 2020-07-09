package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.task.model.TaskLab;

import java.util.UUID;

public class TrashDialogFragment extends DialogFragment {

    private static final String USER_ID = "com.example.task.controller_userId";
    private UUID mUserId;

    private DialogInterface.OnDismissListener mOnDismissListener;

    public TrashDialogFragment(DialogInterface.OnDismissListener listener) {
        mOnDismissListener = listener;
    }

    public static TrashDialogFragment newInstance(DialogInterface.OnDismissListener listener, UUID userId) {
        Bundle args = new Bundle();
        args.putSerializable(USER_ID,userId);
        TrashDialogFragment fragment = new TrashDialogFragment(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
         super.onCreateDialog(savedInstanceState);
        assert getArguments() != null;
        mUserId = (UUID) getArguments().getSerializable(USER_ID);
         return new AlertDialog.Builder(getActivity())
                 .setTitle("are you sure for delete all tasks?")
                 .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         TaskLab.getInstance(getActivity()).clearTasks(mUserId);
                     }
                 })
                 .setNegativeButton(android.R.string.no,null)
                 .create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener!=null)
            mOnDismissListener.onDismiss(dialog);
    }
}
