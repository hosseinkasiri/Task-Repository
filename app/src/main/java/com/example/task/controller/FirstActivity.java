package com.example.task.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.task.R;
import com.example.task.model.User;
import com.example.task.model.UserLab;

public class FirstActivity extends AppCompatActivity {
    private User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

      //  mUser = UserLab.getInstance(getBaseContext()).getFirstUser();

            Intent intent = LoginPageActivity.newIntent(getBaseContext());
            startActivity(intent);
            this.finish();
    }
}