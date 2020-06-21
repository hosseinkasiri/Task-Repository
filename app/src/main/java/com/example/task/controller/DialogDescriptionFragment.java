package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DialogDescriptionFragment extends DialogFragment {

    private TextView mTitle,mDescription,mDate,mTime;
    private Task mTask;
    private static final String ARG_TASK = "com.example.task_TASK";

    public static DialogDescriptionFragment newInstance(Task task) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK,task);
        DialogDescriptionFragment fragment = new DialogDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_description,null);
        findViews(view);
        mTask = (Task) getArguments().getSerializable(ARG_TASK);
        mTitle.setText(mTask.getTitle());
        mDescription.setText(mTask.getDescription());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTask.getDate());
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        mTime.setText(dateFormat.format(date));
        mDate.setText(dateFormat1.format(date));
        return new AlertDialog.Builder(getActivity())
                .setPositiveButton(android.R.string.ok,null)
                .setView(view)
                .create();
    }

    private void findViews(View view) {
        mTitle = view.findViewById(R.id.dialog_title_text);
        mDescription = view.findViewById(R.id.dialog_description_text);
        mDate = view.findViewById(R.id.dialog_date_text);
        mTime = view.findViewById(R.id.dialog_time_text);
    }
}