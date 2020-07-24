package com.example.task.controller;

import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.task.R;
import com.example.task.helper.UpdatableUI;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;
import com.example.task.model.TaskListMode;
import com.example.task.model.User;
import com.example.task.model.UserLab;

import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment implements UpdatableUI {

    private static final String TRASH_TAG = "com.example.task.controller_trash", ADD_TAG = "add tag dialog";
    private static final String ARGS_TASK_MODE = " package com.example.task.task_task mode";
    private static final String USER_ID = "com.example.task.controller_userId";
    private static final String LOG_OUT = "com.example.task.controller_logOut";
    private DialogInterface.OnDismissListener mListener;
    private ImageButton mAddButton;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private List<Task> mTasks;
    private TaskListMode mListMode;
    private Long mUserId;
    private User mUser;
    private SearchView mSearchView;

    public TaskListFragment() {
    }

    public static TaskListFragment newInstance(TaskListMode listMode, Long userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_TASK_MODE, listMode);
        args.putLong(USER_ID, userId);
        TaskListFragment fragment = new TaskListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        mUserId = (Long) getArguments().getSerializable(USER_ID);
        mListener = new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateUi();
            }
        };

        findViews(view);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaskFragment addFragment = AddTaskFragment.newInstance(mListener, mUserId);
                addFragment.show(getFragmentManager(), ADD_TAG);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListMode = (TaskListMode) getArguments().getSerializable(ARGS_TASK_MODE);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_toolbar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.trash_menu:
                TrashDialogFragment trashDialogFragment = TrashDialogFragment.newInstance(mListener, mUserId);
                trashDialogFragment.show(getFragmentManager(), TRASH_TAG);
                break;

            case R.id.log_out_menu:
                mUser = UserLab.getInstance().getUserById(mUserId);
                if (mUser.getGuest()) {
                    LogOutGuestDialogFragment logOutDialogFragment = LogOutGuestDialogFragment.newInstance(mUserId);
                    logOutDialogFragment.show(getFragmentManager(), LOG_OUT);
                }
                else {
                    LogOutDialogFragment logOutDialogFragment = LogOutDialogFragment.newInstance();
                    logOutDialogFragment.show(getFragmentManager(),LOG_OUT);
                }
                break;
            case R.id.action_search:
            //    mSearchView = (SearchView) item.getActionView();
                mSearchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        mAdapter.getFilter().filter(newText);
                        return false;
                    }
                });
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateUi() {
        mTasks =  TaskLab.getInstance().getTasks(mListMode,mUserId);
        mAdapter = new TaskAdapter(getActivity(), mTasks,mListener);
        mRecyclerView.setAdapter(mAdapter);
        if (mTasks.size() != 0){
            mImageView.setVisibility(View.GONE);
        }
        else {
            mImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDisappear() {
        if (mSearchView != null){
            System.out.println("kir");
            mSearchView.setIconified(true);
            mSearchView.setIconified(true);
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
    }
}
