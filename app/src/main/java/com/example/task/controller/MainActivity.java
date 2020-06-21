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

public class MainActivity extends AppCompatActivity {

    private static final String TRASH_TAG = "com.example.task.controller_trash";
    private TabLayout mTabLayout;
    private AppBarLayout mAppBarLayout;
   // private ImageView mTrashView;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private TaskListMode mAll = TaskListMode.all;
    private TaskListMode mDone = TaskListMode.done;
    private TaskListMode mUnDone = TaskListMode.unDone;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context , MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TaskListFragment.newInstance(mAll) , "All" );
        adapter.addFragment(TaskListFragment.newInstance(mDone) , "Done");
        adapter.addFragment(TaskListFragment.newInstance(mUnDone) , "UnDone");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    
    private void findViews() {
        mTabLayout = findViewById(R.id.tab_layout_id);
        mAppBarLayout = findViewById(R.id.app_bar_id);
        mViewPager = findViewById(R.id.view_pager_id);
       // mTrashView = findViewById(R.id.trash_task_id);
    }
}
