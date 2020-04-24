package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mhmsbmrapp.R;
import com.google.android.material.tabs.TabLayout;

public class Fragment1 extends Fragment {
    public  static TabLayout tabLayout;
    public  static ViewPager opviewPager;
    public  static int int_items= 2;



    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment1_layout,null);
        tabLayout=(TabLayout)v.findViewById(R.id.tabs);
        opviewPager=(ViewPager)v.findViewById(R.id.opviewpager);
        //set an adpater

        opviewPager.setAdapter(new MyAdapter( getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(opviewPager);
            }
        });
        return v;
    }

}
