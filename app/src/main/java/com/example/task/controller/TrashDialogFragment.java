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

    private DialogInterface.OnDismissListener mOnDismissListener;

    public TrashDialogFragment(DialogInterface.OnDismissListener listener) {
        mOnDismissListener = listener;
    }

    public static TrashDialogFragment newInstance(DialogInterface.OnDismissListener listener) {
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
                 .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         TaskLab.getInstance().clearTasks();
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
