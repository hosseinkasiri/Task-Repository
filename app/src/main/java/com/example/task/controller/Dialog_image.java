package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.task.R;
import com.example.task.helper.PictureUtils;

import java.io.File;

public class Dialog_image extends DialogFragment {
    private static final String ARGS_FILE = "com.example.task.controller_file";
    private ImageView mImageView;
    private File mPhotoFile;
    public static Dialog_image newInstance(File file) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_FILE , file);
        Dialog_image fragment = new Dialog_image();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.image_dialog,null);
        mImageView = view.findViewById(R.id.dialog_image_view);
        mPhotoFile = (File) getArguments().getSerializable(ARGS_FILE);
        updatePhotoView();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            dismiss();
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotate = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            mImageView.setImageBitmap(rotate);
        }
    }
}
