package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mhmsbmrapp.R;


public class FragmentOP extends Fragment {

    public FragmentOP() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.opbmrfinal, container, false);

        return view;
    }


}



