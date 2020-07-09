package com.example.task.controller;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;
import com.example.task.model.TaskListMode;

import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment{

    private static final String TRASH_TAG = "com.example.task.controller_trash",ADD_TAG = "add tag dialog";
    private static final String ARGS_TASK_MODE = " package com.example.task.task_task mode";
    public static final String USER_ID = "com.example.task.controller_userId";
    private ImageButton mAddButton,mDeleteButton;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private List<Task> mTasks;
    private TaskListMode mListMode;
    private UUID mUserId;

    public TaskListFragment() {
    }

    public static TaskListFragment newInstance(TaskListMode listMode, UUID userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_TASK_MODE , listMode);
        args.putSerializable(USER_ID,userId);
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
                AddTaskFragment addFragment = AddTaskFragment.newInstance(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        updateUi();
                    }
                },mUserId);
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
        mUserId = (UUID) getArguments().getSerializable(USER_ID);
        mTasks =  TaskLab.getInstance(getActivity()).getTasks(mListMode,mUserId);
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
