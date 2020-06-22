package com.example.task;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.CollapsibleActionView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DialogDatePickerFragment extends DialogFragment {

    private DatePicker mDatePicker;
    private Task mTask;
    private static final String ARG_TASK = "com.example.task_Task";

    public DialogDatePickerFragment() {
    }

    public static DialogDatePickerFragment newInstance(Task task) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK,task);
        DialogDatePickerFragment fragment = new DialogDatePickerFragment();
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
                        Date date = new GregorianCalendar(year,month,day).getTime();
                        TaskLab.getInstance().getTask(mTask.getId()).setDate(date);
                    }
                })
                .create();
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