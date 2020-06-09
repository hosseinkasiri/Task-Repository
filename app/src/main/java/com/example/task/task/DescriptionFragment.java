package com.example.task.task;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableRow;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;

import java.io.Serializable;
import java.util.UUID;
public class DescriptionFragment extends Fragment {

    private static final String mTASK_ID = "TASK ID";
    private EditText mDescription,mDate,mTime;
    private Button mEditButton,mDeleteButton,mDoneButton;
    private CheckBox mDoneCheckBox;
    private Task mTask;

    public DescriptionFragment() {
    }

    public static DescriptionFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(mTASK_ID , id);
        DescriptionFragment fragment = new DescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_description, container, false);
        findViews(view);
        UUID taskId = (UUID) getArguments().getSerializable(mTASK_ID);
        mTask = TaskLab.getInstance().getTask(taskId);
        mDescription.setText(mTask.getDescription());
        mDate.setText(mTask.getDate().toString());
        mDoneCheckBox.setChecked(mTask.isDone());
        enable(false);

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDescription.setEnabled(true);
                mDoneCheckBox.setEnabled(true);
                mTask.setDescription(mDescription.getText().toString());
                mDoneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mTask.setDone(isChecked);
                    }
                });
            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.setDescription(mDescription.getText().toString());
                getActivity().finish();
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskLab.getInstance().removeTask(taskId);
                getActivity().finish();
            }
        });



        return view;
    }
    private void findViews(View view){
        mDescription = view.findViewById(R.id.description_id);
        mDate = view.findViewById(R.id.date_description_id);
        mTime = view.findViewById(R.id.time_description_id);
        mEditButton = view.findViewById(R.id.edit_item);
        mDeleteButton = view.findViewById(R.id.delete_item);
        mDoneButton = view.findViewById(R.id.done_item);
        mDoneCheckBox = view.findViewById(R.id.checkbox_description_id);
    }
    private void enable(boolean enable){
        mDescription.setEnabled(enable);
        mDate.setEnabled(enable);
        mTime.setEnabled(enable);
        mDoneCheckBox.setEnabled(enable);
    }
}
