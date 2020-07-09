package com.example.task.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LoginPageActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,LoginPageActivity.class);

        return intent;
    }

    @Override
    public Fragment mFragment() {
        return LoginPageFragment.newInstance();
    }
}