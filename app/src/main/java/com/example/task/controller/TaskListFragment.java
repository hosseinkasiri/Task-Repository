package com.example.task.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.task.R;
import com.example.task.helper.Toaster;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;
import com.example.task.model.TaskListMode;

import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment{

    private ImageButton mAddButton,mDeleteButton;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private List<Task> mTasks;
    private TaskListMode mListMode;
    private static final String TRASH_TAG = "com.example.task.controller_trash",ADD_TAG = "add tag dialog";
    private static final String ARGS_TASK_MODE = " package com.example.task.task_task mode";

    public TaskListFragment() {
    }

    public static TaskListFragment newInstance(TaskListMode listMode) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_TASK_MODE , listMode);
        TaskListFragment fragment = new TaskListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        findViews(view);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment addFragment = AddFragment.newInstance(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        updateUi();
                    }
                });
                addFragment.show(getFragmentManager(),ADD_TAG);
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrashDialogFragment trashDialogFragment = TrashDialogFragment.newInstance(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        updateUi();
                    }
                });
                trashDialogFragment.show(getFragmentManager(),TRASH_TAG);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListMode = (TaskListMode) getArguments().getSerializable(ARGS_TASK_MODE);
        return view;
    }
    public void updateUi() {
        mTasks =  TaskLab.getInstance(getActivity()).getTasks(mListMode);
        mAdapter = new TaskAdapter(getActivity(), mTasks);
        mRecyclerView.setAdapter(mAdapter);
        if (mTasks.size() != 0){
            mImageView.setVisibility(View.GONE);
        }
        else {
            mImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }

    private void findViews(View view) {
        mImageView = view.findViewById(R.id.imageView);
        mAddButton = view.findViewById(R.id.add_button);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mDeleteButton = view.findViewById(R.id.delete_tasks_button);
    }
}
