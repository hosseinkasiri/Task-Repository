package com.example.task.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;

public class SignUpActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,SignUpActivity.class);

        return intent;
    }


    @Override
    public Fragment mFragment() {
        return SignUpFragment.newInstance();
    }
}