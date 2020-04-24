package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mhmsbmrapp.R;
import com.google.android.material.tabs.TabLayout;

public class Fragment4 extends Fragment {
    private static final String TAG = "Fragment4";
    public  static TabLayout RestrainttabLayout;
    public  static ViewPager RestraintviewPager;
    public  static int int_items= 2;



    public Fragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment4_layout,null);
        RestrainttabLayout=(TabLayout)v.findViewById(R.id.Restrainttabs);
        RestraintviewPager=(ViewPager)v.findViewById(R.id.Restraintviewpager);
        //set an adpater

        RestraintviewPager.setAdapter(new RestraintAdapter(getChildFragmentManager()));

        RestrainttabLayout.post(new Runnable() {
            @Override
            public void run() {
                RestrainttabLayout.setupWithViewPager(RestraintviewPager);
            }
        });
        return v;
    }

}