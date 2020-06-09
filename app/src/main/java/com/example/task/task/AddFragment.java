package com.example.task.task;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;
import com.example.task.model.Toaster;

import java.util.Objects;
public class AddFragment extends Fragment {

    private EditText mDescriptionText;
    private TextView mDateText;
    private TextView mTimeText;
    private Task mTask;
    private Button mCancelButton;
    private Button mDoneButton;
    private EditText mTitleText;
    private Toaster mToaster = new Toaster();

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {

        Bundle args = new Bundle();
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        findViews(view);
        mTask = new Task();
        mDateText.setText(mTask.getDate().toString());
        //mTimeText.setText(String.valueOf(mTask.getDate().getTime()));

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTitleText.getText().toString().matches("")){
                    mToaster.makeToast(getActivity() , "please enter title");
                }

                else {
                    Task task = mTask;
                    task.setDescription(mDescriptionText.getText().toString());
                    task.setTitle(mTitleText.getText().toString());
                    TaskLab.getInstance().addTask(task);
                    getActivity().finish();
                }
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }
    private void findViews(View view) {
        mDescriptionText = view.findViewById(R.id.description_edit_id);
        mDateText = view.findViewById(R.id.date_id);
        mTimeText = view.findViewById(R.id.time_text_id);
        mCancelButton = view.findViewById(R.id.cancel_button);
        mDoneButton = view.findViewById(R.id.done_button);
        mTitleText = view.findViewById(R.id.title_edit_id);
    }
}
