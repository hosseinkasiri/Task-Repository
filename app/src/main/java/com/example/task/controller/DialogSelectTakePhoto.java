package com.example.task.controller;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.example.task.helper.PictureUtils;
import com.example.task.helper.Toaster;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

public class DialogSelectTakePhoto extends DialogFragment {

    private final static int REQ_CAMERA = 0;
    private final static int REQ_GALLERY = 55;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final String ARGS_TASK = "com.example.task.controller_task";
    private File mPhotoFile;
    private Task mTask;

    public static DialogSelectTakePhoto newInstance(Task task) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_TASK , task);
        DialogSelectTakePhoto fragment = new DialogSelectTakePhoto();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTask = (Task) getArguments().getSerializable(ARGS_TASK);
        mPhotoFile = TaskLab.getInstance().getPhotoFile(getActivity(),mTask);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        CharSequence[] items = new CharSequence[]{"camera" , "gallery"};
        return new AlertDialog.Builder(getActivity())
                .setTitle("Add image")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("camera")){
                            permissionOfCamera();
                            implicitIntentForCamera();
                        }
                        else if (items[which].equals("gallery")){
                            permissionForGallery();
                            implicitIntentForGallery();
                        }
                    }
                })
                .create();
    }

    private void implicitIntentForGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        Uri uri = getPhotoFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        PackageManager packageManager = getActivity().getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo activity : activities){
            getActivity().grantUriPermission(activity.activityInfo.packageName,uri,
                    intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        startActivityForResult(intent,REQ_GALLERY);
    }

    private void permissionForGallery() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_GALLERY);
        }
    }

    private void implicitIntentForCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = getPhotoFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        PackageManager packageManager = getActivity().getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo activity : activities){
            getActivity().grantUriPermission(activity.activityInfo.packageName,uri,
                    intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        startActivityForResult(Intent.createChooser(intent,"select camera"),REQ_CAMERA);
    }

    private void permissionOfCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA , Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_CAMERA_REQUEST_CODE);
            }
        }
    }

    private Uri getPhotoFileUri() {
        return FileProvider.getUriForFile(getActivity(),"com.example.task.file.provider",mPhotoFile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        Log.d("kos","kos kos kos kos az hame rang");

        if (requestCode == REQ_CAMERA){
            Uri uri = getPhotoFileUri();
            getActivity().revokeUriPermission(uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);



        }

        if (requestCode == REQ_GALLERY){
            Uri uri = getPhotoFileUri();
            getActivity().revokeUriPermission(uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//           try {
////               final Uri imageUri = data.getData();
////
////
////           } catch (FileNotFoundException e) {
////               e.printStackTrace();
////               Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
////           } catch (IOException e) {
////               e.printStackTrace();
////               Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
////           }
        }
    }
}
