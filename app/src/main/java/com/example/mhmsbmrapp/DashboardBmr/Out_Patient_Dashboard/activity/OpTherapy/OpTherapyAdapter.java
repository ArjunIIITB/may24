package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import static com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy.Fragment3.int_items;

public class OpTherapyAdapter extends FragmentPagerAdapter {

    int tabCount1;


    //Constructor to the class
    public OpTherapyAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount1 = tabCount;
    }

    public OpTherapyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OpTherapyMain();
            case 1:
                return new OpTherapyHistory();

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
                return "Therapy";
            case 1:
                return "Therapy History";


        }

        return null;
    }
}
