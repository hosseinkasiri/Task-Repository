package com.example.task.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.helper.PictureUtils;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.checkSelfPermission;
import static androidx.core.content.ContextCompat.getColor;
import static androidx.core.content.ContextCompat.startActivity;

class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String DESCRIPTION_TAG = "description tag",EDIT_TAG = "edit tag";
    private TextView mFirstText , mTitleText , mDateText;
    private Task mTask;
    private File mPhotoFile;
    private ImageView mImageEdit,mImagePhoto;
    private DialogInterface.OnDismissListener mListener;

    public TaskHolder(View itemView, DialogInterface.OnDismissListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        mListener = listener;
        mFirstText = itemView.findViewById(R.id.first_text_show);
        mTitleText = itemView.findViewById(R.id.title_show_text);
        mDateText = itemView.findViewById(R.id.date_show_text);
        mImageEdit = itemView.findViewById(R.id.edit_image_view);
        mImagePhoto = itemView.findViewById(R.id.myImageView);
    }

    public void bind(Task task , Context context){
        mTask = task;
        mPhotoFile = TaskLab.getInstance().getPhotoFile(context,mTask);
        String firstCharacterOfTitle = String.valueOf(task.getTitle().charAt(0));
        mFirstText.setText(firstCharacterOfTitle);
        mTitleText.setText(task.getTitle());
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd         HH:mm:ss");
        mDateText.setText(dateFormat.format(mTask.getDate()));
        updatePhotoView(context);
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
    private void updatePhotoView(Context context) {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mImagePhoto.setImageDrawable(null);
            mImagePhoto.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_button));
        } else {
            Picasso.get().load(mPhotoFile).resize(200,200).into(mImagePhoto);
           mFirstText.setText("");
        }
    }
}