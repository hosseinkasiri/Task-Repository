package com.example.task.task;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

public class DescriptionActivity extends SingleFragmentActivity {

    private static final String mTASK_ID = "TASK ID";
    public static Intent newIntent(Context context , UUID id){
        Intent intent = new Intent(context , DescriptionActivity.class);
        intent.putExtra(mTASK_ID,id);
        return intent;
    }


    @Override
    public Fragment mFragment() {
        UUID taskId = (UUID) getIntent().getSerializableExtra(mTASK_ID);
        return DescriptionFragment.newInstance(taskId);
    }
}
