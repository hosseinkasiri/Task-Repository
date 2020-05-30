package com.example.task;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        return view;
    }
}
