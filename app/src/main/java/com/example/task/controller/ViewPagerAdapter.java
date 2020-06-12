package com.example.task.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.task.model.TaskListMode;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitle = new ArrayList<>();
    private final List<TaskListMode> mFragmentModes = new ArrayList<>();
    private int mPosition;


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }

    public void addFragment(Fragment fragment , String title ){

        mFragments.add(fragment);
        mFragmentTitle.add(title);
    }
}
