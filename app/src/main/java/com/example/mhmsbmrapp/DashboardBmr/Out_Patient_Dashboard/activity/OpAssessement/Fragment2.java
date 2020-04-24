package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mhmsbmrapp.R;
import com.google.android.material.tabs.TabLayout;

public class Fragment2 extends Fragment {
    private static final String TAG = "Fragment2";
    public  static TabLayout tabLayout1;
    public  static ViewPager viewPager1;
    public  static int int_items= 2;



    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment2_layout,null);
        tabLayout1=(TabLayout)v.findViewById(R.id.assessementtabs);
        viewPager1=(ViewPager)v.findViewById(R.id.viewpager1);
        //set an adpater

        viewPager1.setAdapter(new AssessementAdapter(getChildFragmentManager()));

        tabLayout1.post(new Runnable() {
            @Override
            public void run() {
                tabLayout1.setupWithViewPager(viewPager1);
            }
        });
        return v;
    }

}