package com.example.task.controller;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.io.File;
import java.util.List;

public class DialogSelectTakePhoto extends DialogFragment {

    private final static int REQ_CAMERA = 0;
    private static final int REQ_GALLERY = 1;
    private final static int MY_REQ_FOR_GALLERY = 2;
    private static final int MY_REQ_FOR_CAMERA = 3;
    private static final String ARGS_TASK = "com.example.task.controller_task";
    private File mPhotoFile;
    private Task mTask;

    public static int getReqGallery() {
        return REQ_GALLERY;
    }

    public static DialogSelectTakePhoto newInstance(Task task) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_TASK, task);
        DialogSelectTakePhoto fragment = new DialogSelectTakePhoto();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTask = (Task) getArguments().getSerializable(ARGS_TASK);
        mPhotoFile = TaskLab.getInstance().getPhotoFile(getActivity(), mTask);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        CharSequence[] items = new CharSequence[]{"camera", "gallery"};
        return new AlertDialog.Builder(getActivity())
                .setTitle("Add image")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("camera")) {
                            implicitIntentForCamera();
                        } else if (items[which].equals("gallery")) {
                            implicitIntentForGallery();
                    }
                    }
                })
                .create();
    }

    private void implicitIntentForGallery() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_REQ_FOR_GALLERY);
        }
        else {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickPhoto.setType("image/*");
            Uri uri = getPhotoFileUri();
            getTargetFragment().startActivityForResult(pickPhoto, REQ_GALLERY);
        }

    }

    private void implicitIntentForCamera() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, MY_REQ_FOR_CAMERA);

        }
        else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri uri = getPhotoFileUri();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            PackageManager packageManager = getActivity().getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo activity : activities) {
                getActivity().grantUriPermission(activity.activityInfo.packageName, uri,
                        intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            getTargetFragment().startActivityForResult(intent, REQ_CAMERA);
        }
    }

    private Uri getPhotoFileUri() {
        return FileProvider.getUriForFile(getActivity(), "com.example.task.file.provider", mPhotoFile);
    }
}

