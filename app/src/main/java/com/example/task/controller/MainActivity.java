package com.example.task.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;
import com.example.task.model.TaskListMode;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID = "com.example.task.controller_userId";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private TaskListMode mAll = TaskListMode.all;
    private TaskListMode mDone = TaskListMode.done;
    private TaskListMode mUnDone = TaskListMode.unDone;
    private UUID mUserId;

    public static Intent newIntent(Context context, UUID userId){
        Intent intent = new Intent(context , MainActivity.class);
        intent.putExtra(USER_ID,userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        mUserId = (UUID) getIntent().getSerializableExtra(USER_ID);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TaskListFragment.newInstance(mAll,mUserId) , "All" );
        adapter.addFragment(TaskListFragment.newInstance(mDone,mUserId) , "Done");
        adapter.addFragment(TaskListFragment.newInstance(mUnDone,mUserId) , "UnDone");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    
    private void findViews() {
        mTabLayout = findViewById(R.id.tab_layout_id);
        mViewPager = findViewById(R.id.view_pager_id);
    }
}
