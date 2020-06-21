package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DialogEditFragment extends DialogFragment {

    private static final String ARG_TASK = "com.example.task_task";
    private TextView mTitleText;
    private EditText mDescriptionText;
    private DatePicker mDatePicker;
    private Task mTask;

    public DialogEditFragment() {
    }

    public static DialogEditFragment newInstance(Task task) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK,task);
        DialogEditFragment fragment = new DialogEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_edit,null);
        findViews(view);
        mTask = (Task) getArguments().getSerializable(ARG_TASK);
        mTitleText.setText(mTask.getTitle());
        mDescriptionText.setText(mTask.getDescription());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTask.getDate());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year,month,day,null);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskLab.getInstance().getTask(mTask.getId()).setDescription(mDescriptionText.getText().toString());
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year,month,day).getTime();
                        TaskLab.getInstance().getTask(mTask.getId()).setDate(date);
                    }
                })
                .setNegativeButton(android.R.string.cancel,null)
                .create();
    }

    private void findViews(View view) {
        mTitleText = view.findViewById(R.id.dialog_edit_title);
        mDescriptionText = view.findViewById(R.id.dialog_edit_description);
        mDatePicker = view.findViewById(R.id.dialog_date_picker);
    }
}