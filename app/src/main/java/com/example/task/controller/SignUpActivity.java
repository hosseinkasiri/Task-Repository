package com.example.task.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.UUID;

public class SignUpActivity extends SingleFragmentActivity {

    private static final String USER_ID = "com.example.task.controller_userId";

    public static Intent newIntent(Context context, UUID userId){
        Intent intent = new Intent(context,SignUpActivity.class);
        intent.putExtra(USER_ID,userId);
        return intent;
    }

    @Override
    public Fragment mFragment() {
        UUID UserId = (UUID) getIntent().getSerializableExtra(USER_ID);
        return SignUpFragment.newInstance(UserId);
    }
}