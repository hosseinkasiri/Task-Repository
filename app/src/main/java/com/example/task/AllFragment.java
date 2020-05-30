package com.example.task;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {

    private ImageButton mAddButton;
    private ImageView mImageView;

    public AllFragment() {
        // Required empty public constructor
    }

    public static AllFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all, container, false);

        mImageView = view.findViewById(R.id.imageView);
        mAddButton = view.findViewById(R.id.add_button);

        return view;
    }
}
