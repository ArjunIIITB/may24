package com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhmsbmrapp.R;
import com.google.android.material.tabs.TabLayout;

public class In_Patientbmr_TabFragment extends Fragment {
    public  static TabLayout tabLayout1;
    public  static ViewPager viewPager1;
    public  static int int_items= 3;


    public In_Patientbmr_TabFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ip_patientbmr_fragment, container, false);

        tabLayout1=(TabLayout)v.findViewById(R.id.Ip_bmr_tabs);
        viewPager1=(ViewPager)v.findViewById(R.id.viewpager1);
        //set an adpater

        viewPager1.setAdapter(new Ip_bmr_AdapterMain(getChildFragmentManager()));

        tabLayout1.post(new Runnable() {
            @Override
            public void run() {
                tabLayout1.setupWithViewPager(viewPager1);
            }
        });
        return v;

    }
}
