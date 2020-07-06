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
import android.widget.TimePicker;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.util.Calendar;
import java.util.Date;

public class DialogTimePickerFragment extends DialogFragment {

    private TimePicker mTimePicker;
    private Task mTask;
    private static final String ARG_TASK_TIME = "com.example.task_Task";

    public DialogTimePickerFragment() {
    }

    public static DialogTimePickerFragment newInstance(Task task) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_TIME,task);
        DialogTimePickerFragment fragment = new DialogTimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_time_picker,null);
        mTimePicker = view.findViewById(R.id.time_time_picker_dialog);
        mTask = (Task) getArguments().getSerializable(ARG_TASK_TIME);
        setTimeTimePicker();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = mTimePicker.getHour();
                        int minute = mTimePicker.getMinute();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(calendar.HOUR_OF_DAY,hour);
                        calendar.set(calendar.MINUTE,minute);
                        Date date = calendar.getTime();
                        TaskLab.getInstance(getActivity()).getTask(mTask.getId()).setDate(date);
                    }
                })
                .create();
    }

    private void setTimeTimePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTask.getDate());
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        mTimePicker.setHour(hour);
        mTimePicker.setMinute(minute);
    }
}