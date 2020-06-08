package com.example.task;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {

    private EditText mDescription,mDate,mTime;
    private Button mEditButton,mDeleteButton,mDoneButton;
    private CheckBox mDoneCheckBox;



    public DescriptionFragment() {
        // Required empty public constructor
    }

    public static DescriptionFragment newInstance() {

        Bundle args = new Bundle();

        DescriptionFragment fragment = new DescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        findViews(view);
        return view;
    }
    private void findViews(View view){

        mDescription = view.findViewById(R.id.description_id);
        mDate = view.findViewById(R.id.date_description_id);
        mTime = view.findViewById(R.id.time_description_id);
        mEditButton = view.findViewById(R.id.edit_item);
        mDeleteButton = view.findViewById(R.id.delete_item);
        mDoneButton = view.findViewById(R.id.done_item);
        mDoneCheckBox = view.findViewById(R.id.checkbox_description_id);
    }
}
