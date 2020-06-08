package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.task.task.SingleFragmentActivity;

public class DescriptionActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context , DescriptionActivity.class);
        return intent;
    }

    @Override
    public Fragment mFragment() {
        return DescriptionFragment.newInstance();
    }
}
