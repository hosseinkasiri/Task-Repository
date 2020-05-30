package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private AppBarLayout mAppBarLayout;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tab_layout_id);
        mAppBarLayout = findViewById(R.id.app_bar_id);
        mViewPager = findViewById(R.id.view_pager_id);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(AllFragment.newInstance() , "All");
        adapter.addFragment(DoneFragment.newInstance() , "Done");
        adapter.addFragment(UnDoneFragment.newInstance() , "UnDone");

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }
}
