package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mhmsbmrapp.R;
import com.google.android.material.tabs.TabLayout;

public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment2";
    public  static TabLayout therapytabLayout;
    public  static ViewPager therapyviewPager;
    public  static int int_items= 2;



    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment3_layout,null);
        therapytabLayout=(TabLayout)v.findViewById(R.id.therapytabs);
        therapyviewPager=(ViewPager)v.findViewById(R.id.therapyviewpager);
        //set an adpater

        therapyviewPager.setAdapter(new OpTherapyAdapter(getChildFragmentManager()));

        therapytabLayout.post(new Runnable() {
            @Override
            public void run() {
                therapytabLayout.setupWithViewPager(therapyviewPager);
            }
        });
        return v;
    }

}
