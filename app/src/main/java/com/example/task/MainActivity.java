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

        adapter.addFragment(new AllFragment() , "All");
        adapter.addFragment(new DoneFragment() , "Done");
        adapter.addFragment(new UnDoneFragment() , "UnDone");

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }
}
