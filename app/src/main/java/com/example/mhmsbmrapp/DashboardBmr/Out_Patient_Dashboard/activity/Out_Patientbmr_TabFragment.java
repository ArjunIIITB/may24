package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mhmsbmrapp.AddPatientFolder.AddPatients;
import com.example.mhmsbmrapp.R;
import com.google.android.material.tabs.TabLayout;

public class Out_Patientbmr_TabFragment extends Fragment {

    TextView testView;
    public  static TabLayout tabLayout2;
    public  static ViewPager viewPager2;
    public  static int int_items= 2;


    public Out_Patientbmr_TabFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.op_patientbmr_fragment, container, false);



        tabLayout2=(TabLayout)v.findViewById(R.id.Op_bmr_tabs);
        viewPager2=(ViewPager)v.findViewById(R.id.opviewpager);
        //set an adpater

        viewPager2.setAdapter(new Out_bmr_AdapterMain(getChildFragmentManager()));

        tabLayout2.post(new Runnable() {
            @Override
            public void run() {
                tabLayout2.setupWithViewPager(viewPager2);
            }
        });

        testView=(TextView)v.findViewById(R.id.AddPatient);
        testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), AddPatients.class);
                startActivity(intent);
            }
        });
        return v;

    }
}