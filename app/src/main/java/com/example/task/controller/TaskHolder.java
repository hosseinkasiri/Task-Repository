package com.example.task.controller;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mFirstText , mTitleText , mDateText;
    private Task mTask;
    private ImageView mImageEdit;
    private DialogInterface.OnDismissListener mListener;
    private static final String DESCRIPTION_TAG = "description tag",EDIT_TAG = "edit tag";

    public TaskHolder(View itemView, DialogInterface.OnDismissListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        mListener = listener;
        mFirstText = itemView.findViewById(R.id.first_text_show);
        mTitleText = itemView.findViewById(R.id.title_show_text);
        mDateText = itemView.findViewById(R.id.date_show_text);
        mImageEdit = itemView.findViewById(R.id.edit_image_view);
    }

    public void bind(Task task){
        mTask = task;
        String firstCharacterOfTitle = String.valueOf(task.getTitle().charAt(0));
        mFirstText.setText(firstCharacterOfTitle);
        mTitleText.setText(task.getTitle());
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd         HH:mm:ss");
        mDateText.setText(dateFormat.format(mTask.getDate()));
        mImageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditFragment dialogEditFragment = DialogEditFragment.newInstance(mTask,mListener);
                dialogEditFragment.show(((AppCompatActivity)v.getContext()).getSupportFragmentManager(),EDIT_TAG);
            }
        });
    }
    @Override
    public void onClick(View v) {
        DialogDescriptionFragment descriptionFragment = DialogDescriptionFragment.newInstance(mTask);
        descriptionFragment.show(((AppCompatActivity)v.getContext()).getSupportFragmentManager(),DESCRIPTION_TAG);
    }
}
