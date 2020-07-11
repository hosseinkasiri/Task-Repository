package com.example.task.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.task.R;
import com.example.task.helper.UpdatableUI;
import com.example.task.model.Task;
import com.example.task.model.TaskLab;
import com.example.task.model.TaskListMode;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID = "com.example.task.controller_userId";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private UUID mUserId;
    private ArrayList<UpdatableUI> fragments;

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
        fragments = new ArrayList<>();
        fragments.add(TaskListFragment.newInstance(TaskListMode.all,mUserId));
        fragments.add(TaskListFragment.newInstance(TaskListMode.done,mUserId));
        fragments.add(TaskListFragment.newInstance(TaskListMode.unDone,mUserId));
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment((Fragment) fragments.get(0) , "All" );
        adapter.addFragment((Fragment) fragments.get(1) , "Done");
        adapter.addFragment((Fragment) fragments.get(2), "UnDone");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
              fragments.get(position).updateUi();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    
    private void findViews() {
        mTabLayout = findViewById(R.id.tab_layout_id);
        mViewPager = findViewById(R.id.view_pager_id);
    }
}
