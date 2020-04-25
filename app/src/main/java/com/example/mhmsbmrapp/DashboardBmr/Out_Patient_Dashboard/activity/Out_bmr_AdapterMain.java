package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpbmrCompletedList.Out_Completed_list;

import static com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.Out_Patientbmr_TabFragment.int_items;

public class Out_bmr_AdapterMain extends FragmentPagerAdapter {

    int tabCount1;


    //Constructor to the class
    public Out_bmr_AdapterMain(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount1 = tabCount;
    }

    public Out_bmr_AdapterMain(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Out_Patientbmr();
            case 1:
                return new Out_Completed_list();

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
                return "Queued Out";
            case 1:
                return "Completed Out";


        }

        return null;
    }
}