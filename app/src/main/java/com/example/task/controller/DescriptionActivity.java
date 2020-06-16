package com.example.task.controller;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.task.model.Task;

public class DescriptionActivity extends SingleFragmentActivity {

    private static final String mTASK_ID = "TASK ID";
    public static Intent newIntent(Context context , Task task){
        Intent intent = new Intent(context , DescriptionActivity.class);
        intent.putExtra(mTASK_ID, task);
        return intent;
    }

    @Override
    public Fragment mFragment() {
        Task task = (Task) getIntent().getSerializableExtra(mTASK_ID);
        return DescriptionFragment.newInstance(task);
    }
}
