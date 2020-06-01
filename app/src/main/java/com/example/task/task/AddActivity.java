package com.example.task.task;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

public class AddActivity extends SingleFragmentActivity {

    public static Intent newIntent (Context context){

        Intent intent = new Intent(context , AddActivity.class);

        return intent;
    }



    @Override
    public Fragment mFragment() {
        return AddFragment.newInstance();
    }
}
