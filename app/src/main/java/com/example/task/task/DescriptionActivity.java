package com.example.task.task;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

public class DescriptionActivity extends SingleFragmentActivity {

    private static final String mTASK_ID = "TASK ID";
    public static Intent newIntent(Context context ){
        Intent intent = new Intent(context , DescriptionActivity.class);
        //intent.putExtra(mTASK_ID,TaskId);
        return intent;
    }

   // UUID taskId = (UUID) getIntent().getSerializableExtra(mTASK_ID);
    @Override
    public Fragment mFragment() {
        return DescriptionFragment.newInstance();
    }
}
