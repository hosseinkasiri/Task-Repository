package com.example.task.task;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.task.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment mFragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        FragmentManager manager = getSupportFragmentManager();
        if (manager.findFragmentById(R.id.activity_add) == null){

            manager.beginTransaction()
                    .add(R.id.activity_add , mFragment())
                    .commit();

        }



    }
}
