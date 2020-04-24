package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import static com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring.Fragment4.int_items;

public class RestraintAdapter extends FragmentPagerAdapter {

    int tabCount1;


    //Constructor to the class
    public RestraintAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount1 = tabCount;
    }

    public RestraintAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RestraintMain();
            case 1:
                return new RestraintHistory();

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
                return "Restraint Monitoring";
            case 1:
                return "Restraint Monitoring History";


        }

        return null;
    }
}
