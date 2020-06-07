package com.example.task.task;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {

    private ImageButton mAddButton;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private List<Task> mTasks ;
    private static final String ARGS_TASK_MODE = " package com.example.task.task_task mode";


    public static AllFragment newInstance(TaskListMode listMode) {
        
        Bundle args = new Bundle();
        args.putSerializable(ARGS_TASK_MODE , listMode);
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all, container, false);

        findViews(view);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = AddActivity.newIntent(getActivity());
                startActivityForResult(intent , 0);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TaskListMode listMode = (TaskListMode) getArguments().getSerializable(ARGS_TASK_MODE);
        mTasks =  TaskLab.getInstance().getTasks(listMode);
        TaskAdapter adapter = new TaskAdapter(getActivity() , mTasks);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    private void findViews(View view) {
        mImageView = view.findViewById(R.id.imageView);
        mAddButton = view.findViewById(R.id.add_button);
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }
}
