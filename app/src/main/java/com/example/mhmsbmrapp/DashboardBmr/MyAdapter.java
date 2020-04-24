package com.example.mhmsbmrapp.DashboardBmr;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.In_Patientbmr_TabFragment;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.Out_Patientbmr_TabFragment;

import static com.example.mhmsbmrapp.DashboardBmr.TabFragment.int_items;

public class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Out_Patientbmr_TabFragment();
            case 1:
                return new In_Patientbmr_TabFragment();



        }
        return null;
    }

    @Override
    public int getCount() {


        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Out-Patient";
            case 1:
                return "In-Patient";


        }

        return null;
    }
}
