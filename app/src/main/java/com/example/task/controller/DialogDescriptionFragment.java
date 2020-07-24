package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.helper.PictureUtils;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DialogDescriptionFragment extends DialogFragment {

    private TextView mTitle,mDescription,mDate,mTime,mTextLetter;
    private Task mTask;
    private File mPhotoFile;
    private ImageView mDescriptionImage;
    private static final String ARG_TASK = "com.example.task_TASK" , TAG_IMAGE = "image_tag";

    public static DialogDescriptionFragment newInstance(Task task) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK,task);
        DialogDescriptionFragment fragment = new DialogDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTask = (Task) getArguments().getSerializable(ARG_TASK);
        mPhotoFile = TaskLab.getInstance().getPhotoFile(getActivity(),mTask);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_description,null);
        findViews(view);
        mTitle.setText(mTask.getTitle());
        mTextLetter.setText(String.valueOf(mTitle.getText().charAt(0)));
        mDescription.setText(mTask.getDescription());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTask.getDate());
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        mTime.setText(dateFormat.format(date));
        mDate.setText(dateFormat1.format(date));
        updatePhotoView();
        mDescriptionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_image dialog_image = Dialog_image.newInstance(mPhotoFile);
                dialog_image.show(getFragmentManager(),TAG_IMAGE);
            }
        });
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
        mDescriptionImage = view.findViewById(R.id.dialog_description_image);
        mTextLetter = view.findViewById(R.id.description_first_letter);
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mDescriptionImage.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotate = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            mDescriptionImage.setImageBitmap(rotate);
            mTextLetter.setText("");
        }
    }
}