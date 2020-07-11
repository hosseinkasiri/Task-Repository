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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DialogEditFragment extends DialogFragment {

    private static final String ARG_TASK = "com.example.task_task",DATE_TAG = "Date",TIME_TAG = "Time";
    private TextView mTitleText;
    private EditText mDescriptionText;
    private Button mDateButton,mTimeButton;
    private CheckBox mDoneCheckBox;
    private Task mTask;
    private DialogInterface.OnDismissListener mOnDismissListener;

    public DialogEditFragment(DialogInterface.OnDismissListener listener) {
        mOnDismissListener = listener;
    }

    public static DialogEditFragment newInstance(Task task, DialogInterface.OnDismissListener listener) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK,task);
        DialogEditFragment fragment = new DialogEditFragment(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_edit,null);
        findViews(view);
        setTextAttribute();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnDismissListener dismissListener =  new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        setTextAttribute();
                    }
                };
                DialogDatePickerFragment datePickerFragment = DialogDatePickerFragment.newInstance(mTask,dismissListener);
                datePickerFragment.show(getFragmentManager(),DATE_TAG);

            }
        });
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogTimePickerFragment timePickerFragment = DialogTimePickerFragment.newInstance(mTask,mOnDismissListener);
                timePickerFragment.show(getFragmentManager(),TIME_TAG);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTask.setDescription(mDescriptionText.getText().toString());
                        mTask.setDone(mDoneCheckBox.isChecked());
                        TaskLab.getInstance(getActivity()).updateTask(mTask);
                    }
                })
                .setNegativeButton(android.R.string.cancel,null)
                .create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener!=null)
            mOnDismissListener.onDismiss(dialog);
    }

    private void setTextAttribute() {
        mTask = (Task) getArguments().getSerializable(ARG_TASK);
        mTitleText.setText(mTask.getTitle());
        mDescriptionText.setText(mTask.getDescription());
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        mDateButton.setText(dateFormat.format(mTask.getDate()));
        DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
        mTimeButton.setText(timeFormat.format(mTask.getDate()));
        mDoneCheckBox.setChecked(mTask.isDone());
    }

    private void findViews(View view) {
        mTitleText = view.findViewById(R.id.dialog_edit_title);
        mDescriptionText = view.findViewById(R.id.dialog_edit_description);
        mDateButton = view.findViewById(R.id.date_picker_button);
        mTimeButton = view.findViewById(R.id.time_picker_button);
        mDoneCheckBox = view.findViewById(R.id.dialog_done_check_box);
    }
}