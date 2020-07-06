package com.example.task.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class LoginPageActivity extends SingleFragmentActivity {

    @Override
    public Fragment mFragment() {
        return LoginPageFragment.newInstance();
    }
}