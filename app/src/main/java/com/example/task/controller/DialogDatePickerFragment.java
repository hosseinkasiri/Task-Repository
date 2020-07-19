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

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DialogDatePickerFragment extends DialogFragment {

    private DatePicker mDatePicker;
    private Task mTask;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private static final String ARG_TASK = "com.example.task_Task";

    public DialogDatePickerFragment(DialogInterface.OnDismissListener listener) {
        mOnDismissListener = listener;
    }

    public static DialogDatePickerFragment newInstance(Task task, DialogInterface.OnDismissListener listener) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK,task);
        DialogDatePickerFragment fragment = new DialogDatePickerFragment(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_date_picker,null);
        mDatePicker = view.findViewById(R.id.date_date_picker_dialog);
        mTask = (Task) getArguments().getSerializable(ARG_TASK);
        setDatePicker();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(mTask.getDate());
                        int hour = calendar.get(Calendar.HOUR);
                        int minute = calendar.get(Calendar.MINUTE);
                        Date date = new GregorianCalendar(year,month,day).getTime();
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.setTime(date);
                        calendar1.set(calendar.HOUR_OF_DAY,hour);
                        calendar1.set(calendar.MINUTE,minute);
                        Date date1 = calendar1.getTime();
                        mTask.setDate(date1);
                        TaskLab.getInstance().updateTask(mTask);

                    }
                })
                .create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null)
            mOnDismissListener.onDismiss(dialog);
    }

    private void setDatePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTask.getDate());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year,month,day,null);
    }
}