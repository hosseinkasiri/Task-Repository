package com.example.task.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.helper.PictureUtils;
import com.example.task.helper.Toaster;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DialogEditFragment extends DialogFragment {

    private static final String ARG_TASK = "com.example.task_task",DATE_TAG = "Date",TIME_TAG = "Time";
    private static final String IMAGE_TAG = "image_tag";
    private TextView mTitleText;
    private EditText mDescriptionText;
    private Button mDateButton,mTimeButton;
    private ImageButton mShareButton;
    private ImageView mEditImage;
    private CheckBox mDoneCheckBox;
    private Task mTask;
    private File mPhotoFile;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTask = (Task) getArguments().getSerializable(ARG_TASK);
        mPhotoFile = TaskLab.getInstance().getPhotoFile(getActivity(),mTask);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_edit,null);
        findViews(view);
        setTextAttribute();
        updatePhotoView();
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
                DialogInterface.OnDismissListener dismissListener = new
                        DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                setTextAttribute();
                            }
                        };
                DialogTimePickerFragment timePickerFragment = DialogTimePickerFragment.newInstance(mTask,dismissListener);
                timePickerFragment.show(getFragmentManager(),TIME_TAG);
            }
        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,getTextStringTask());
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent,getResources().getText(R.string.share_task)));
            }
        });

        mEditImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogSelectTakePhoto dialogSelectTakePhoto = DialogSelectTakePhoto.newInstance(mTask);
                dialogSelectTakePhoto.show(getFragmentManager(),IMAGE_TAG);
            }
        });


        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTask.setDescription(mDescriptionText.getText().toString());
                        mTask.setDone(mDoneCheckBox.isChecked());
                        TaskLab.getInstance().updateTask(mTask);
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
        mTitleText.setText(mTask.getTitle());
        mDescriptionText.setText(mTask.getDescription());
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        mDateButton.setText(dateFormat.format(mTask.getDate()));
        DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
        mTimeButton.setText(timeFormat.format(mTask.getDate()));
        mDoneCheckBox.setChecked(mTask.getDone());
    }

    private void findViews(View view) {
        mTitleText = view.findViewById(R.id.dialog_edit_title);
        mDescriptionText = view.findViewById(R.id.dialog_edit_description);
        mDateButton = view.findViewById(R.id.date_picker_button);
        mTimeButton = view.findViewById(R.id.time_picker_button);
        mDoneCheckBox = view.findViewById(R.id.dialog_done_check_box);
        mShareButton = view.findViewById(R.id.share_task_button);
        mEditImage = view.findViewById(R.id.dialog_edit_image);
    }

    private String getTextStringTask(){
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String dateString = dateFormat.format(mTask.getDate());
        DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
        String timeString = timeFormat.format(mTask.getDate());
        String doneString = mTask.getDone() ? getString(R.string.is_done) : getString( R.string.is_not_done);
       return getString(R.string.share_task_text,mTask.getTitle(), mTask.getDescription(),dateString,timeString,doneString);
    }

    private void updatePhotoView(){
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mEditImage.setImageDrawable(null);
        }
        else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(),getActivity());
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotate = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            mEditImage.setImageBitmap(rotate);
        }
    }
}