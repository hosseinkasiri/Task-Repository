package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;
import com.example.task.helper.Toaster;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class AddTaskFragment extends DialogFragment{

    private static final String USER_ID = "com.example.task.controller_userId";
    private EditText mDescriptionText;
    private TextView mDateText;
    private TextView mTimeText;
    private Task mTask;
    private EditText mTitleText;
    private TextView mTitleTextView;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private Long mUserId;

    public AddTaskFragment(DialogInterface.OnDismissListener listener) {
        // Required empty public constructor
        mOnDismissListener = listener;
    }

    public static AddTaskFragment newInstance(DialogInterface.OnDismissListener listener, Long userId) {
        Bundle args = new Bundle();
        args.putLong(USER_ID,userId);
        AddTaskFragment fragment = new AddTaskFragment(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add,null);
        findViews(view);
        setTitleText();
        mUserId = (Long) getArguments().getSerializable(USER_ID);
        mTask = new Task();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        mDateText.setText(dateFormat.format(mTask.getDate()));
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        mTimeText.setText(timeFormat.format(mTask.getDate()));
        mTask.setUserId(mUserId);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mTitleText.getText().toString().matches(""))
                            Toaster.makeToast(getActivity(),"please write title!!!");

                        else {
                            Task task = mTask;
                            task.setDescription(mDescriptionText.getText().toString());
                            task.setTitle(mTitleText.getText().toString());
                            task.setUserId(mUserId);
                            TaskLab.getInstance().addTask(task);
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel,null)
                .create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null)
            mOnDismissListener.onDismiss(dialog);

    }

    private void findViews(View view) {
        mDescriptionText = view.findViewById(R.id.description_edit_id);
        mDateText = view.findViewById(R.id.date_id);
        mTimeText = view.findViewById(R.id.time_text_id);
        mTitleText = view.findViewById(R.id.title_edit_id);
        mTitleTextView = view.findViewById(R.id.title_text_view);
    }

    private void setTitleText() {
        String text = "Title</font> <font color=#FF0000> *</font>";
        mTitleTextView.setText(Html.fromHtml(text,0));
    }
}
